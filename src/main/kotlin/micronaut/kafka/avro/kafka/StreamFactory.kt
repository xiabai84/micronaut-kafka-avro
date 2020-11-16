package micronaut.kafka.avro.kafka

import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde
import io.micronaut.configuration.kafka.streams.ConfiguredStreamBuilder
import io.micronaut.context.annotation.Factory
import io.micronaut.context.annotation.Value
import micronaut.kafka.avro.config.StoreConfig
import micronaut.kafka.avro.config.TopicConfig
import micronaut.kafka.avro.model.Partner
import micronaut.kafka.avro.model.PartnerView
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.StreamsConfig
import org.apache.kafka.streams.kstream.Consumed
import org.apache.kafka.streams.kstream.KStream
import org.apache.kafka.streams.kstream.Materialized
import org.apache.kafka.streams.state.Stores
import java.util.*
import javax.inject.Singleton


@Factory
class StreamFactory(
        private val topics: TopicConfig,
        private val stores: StoreConfig
) {

    private val env = System.getenv()

    @Value("\${kafka.bootstrap.servers}")
    var bootstrapServers: String = ""

    @Value("\${kafka.schema.registry.url}")
    var schemaRegistryUrl: String = ""

    @Singleton
    fun buildPartnerTopology(builder: ConfiguredStreamBuilder): KStream<String, Partner>? {

        val partnerStore = Stores.inMemoryKeyValueStore(stores.partnerStore)

        val registryUrl = env["SCHEMA_REGISTRY_URL_CONFIG"] ?: schemaRegistryUrl

        val streamsConfig = Properties()

        streamsConfig.apply {
            put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String()::class.java.canonicalName)
            put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, SpecificAvroSerde::class.java.canonicalName)
            put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, env["BOOTSTRAP_SERVERS"] ?: bootstrapServers)
            put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")
            put("schema.registry.url", registryUrl)
        }

        val serdeConfig = mapOf("schema.registry.url" to registryUrl)
        val valueSerde = SpecificAvroSerde<Partner>()
        valueSerde.configure(serdeConfig, false)

        val stream = builder.stream(
                topics.partnerTopic,
                Consumed.with(Serdes.String(), valueSerde)
        )

        stream
            .groupByKey()
            .reduce(
                    { _, v2 -> v2 },
                    Materialized.`as`<String, Partner>(partnerStore)
                        .withKeySerde(Serdes.String()).withValueSerde(valueSerde)
            )

        stream.foreach { key, value ->
            println("###original $key, $value")
            val partner = PartnerView(
                    id = value.id.toString(),
                    vorname = value.vorname.toString(),
                    nachname = value.nachname.toString(),
                    age = value.age,
                    email = value.email?.toString()
            )
            println("partner: $partner")
        }

        return stream
    }
}
