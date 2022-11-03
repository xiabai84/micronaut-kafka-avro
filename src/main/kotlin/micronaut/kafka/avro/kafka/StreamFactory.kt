package micronaut.kafka.avro.kafka

import com.fasterxml.jackson.databind.ObjectMapper
import io.confluent.kafka.serializers.json.KafkaJsonSchemaSerializer
import io.confluent.kafka.streams.serdes.json.KafkaJsonSchemaSerde
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
        private val stores: StoreConfig,
        private val jsonSchemaSerdes: JsonSchemaSerdes
) {

    private val env = System.getenv()

    @Value("\${kafka.bootstrap.servers}")
    var bootstrapServers: String = ""

    @Singleton
    fun buildPartnerTopology(builder: ConfiguredStreamBuilder): KStream<String, Partner>? {

        val partnerStore = Stores.inMemoryKeyValueStore(stores.partnerStore)

        val streamsConfig = Properties()

        streamsConfig.apply {
            put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, env["BOOTSTRAP_SERVERS"] ?: bootstrapServers)
            put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")
        }

        val stream = builder.stream(
                topics.partnerTopic,
                Consumed.with(Serdes.String(), jsonSchemaSerdes.value(Partner::class.java))
        )

        stream
            .groupByKey()
            .reduce(
                    { _, v2 -> v2 },
                    Materialized.`as`<String, Partner>(partnerStore)
                        .withKeySerde(Serdes.String()).withValueSerde(jsonSchemaSerdes.value(Partner::class.java))
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
