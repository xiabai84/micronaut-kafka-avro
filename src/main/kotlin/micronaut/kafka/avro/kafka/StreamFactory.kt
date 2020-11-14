package micronaut.kafka.avro.kafka

import io.confluent.kafka.streams.serdes.avro.GenericAvroSerde
import io.micronaut.configuration.kafka.streams.ConfiguredStreamBuilder
import io.micronaut.context.annotation.Factory
import io.micronaut.context.annotation.Value
import micronaut.kafka.avro.config.TopicConfig
import micronaut.kafka.avro.model.toPartnerV1
import micronaut.kafka.avro.model.toPartnerV2
import micronaut.kafka.avro.model.toPartnerV3
import org.apache.avro.generic.GenericRecord
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.StreamsConfig
import org.apache.kafka.streams.kstream.Consumed
import org.apache.kafka.streams.kstream.KStream
import java.util.*
import javax.inject.Singleton


@Factory
class StreamFactory(private val topics: TopicConfig) {

    private val env = System.getenv()

    @Value("\${kafka.bootstrap.servers}")
    var bootstrapServers: String = ""

    @Value("\${kafka.schema.registry.url}")
    var schemaRegistryUrl: String = ""

    @Singleton
    fun buildPartnerTopology(builder: ConfiguredStreamBuilder): KStream<String, GenericRecord>? {

        val registryUrl = env["SCHEMA_REGISTRY_URL_CONFIG"] ?: schemaRegistryUrl

        val streamsConfig = Properties()

        streamsConfig.apply {
            put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String()::class.java.canonicalName)
            put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, GenericAvroSerde::class.java.canonicalName)
            put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, env["BOOTSTRAP_SERVERS"] ?: bootstrapServers)
            put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")
            put("schema.registry.url", registryUrl)
        }

        val valueSerde = GenericAvroSerde()
        val serdeConfig = mapOf("schema.registry.url" to registryUrl)

        valueSerde.configure(serdeConfig, false)

        val stream = builder.stream(
                topics.partnerTopic,
                Consumed.with(Serdes.String(), valueSerde)
        )

        stream.foreach { key, value ->
            println("###original $key, $value")
            val partnerV1 = value.toPartnerV1()
            println("#####$key, $partnerV1")

//            val partnerV2 = value.toPartnerV2()
//            println("#####$key, $partnerV2")
//
//            val partnerV3 = value.toPartnerV3()
//            println("#####$key, $partnerV3")
        }

        return stream
    }
}
