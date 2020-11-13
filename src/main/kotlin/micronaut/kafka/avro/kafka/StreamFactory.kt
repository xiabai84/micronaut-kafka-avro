package micronaut.kafka.avro.kafka

import io.confluent.kafka.streams.serdes.avro.GenericAvroSerde
import io.micronaut.configuration.kafka.streams.ConfiguredStreamBuilder
import io.micronaut.context.annotation.Factory
import io.micronaut.context.annotation.Value
import micronaut.kafka.avro.config.TopicConfig
import micronaut.kafka.avro.model.toPartnerV1
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.StreamsConfig
import org.apache.kafka.streams.Topology
import org.apache.kafka.streams.kstream.Consumed
import javax.inject.Singleton

@Factory
class StreamFactory(private val topics: TopicConfig) {

    private val env = System.getenv()

    @Value("\${kafka.bootstrap.servers}")
    var bootstrapServers: String = ""

    @Value("\${kafka.schema.registry.url}")
    var schemaRegistryUrl: String = ""

    @Singleton
    fun buildPartnerTopology(builder: ConfiguredStreamBuilder): Topology? {

        val prop = builder.configuration

        prop.apply {
            put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.StringSerde::class.java.canonicalName)
            put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, GenericAvroSerde::class.java.canonicalName)
            put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, env["SCHEMA_REGISTRY_URL_CONFIG"] ?: bootstrapServers)
            put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")
            put("schema.registry.url", env["SCHEMA_REGISTRY_URL_CONFIG"] ?: schemaRegistryUrl)
        }

        builder.stream(
                topics.partnerTopic,
                Consumed.with(Serdes.String(), GenericAvroSerde())
        )
        .foreach { key, value ->
            val partner = value.toPartnerV1()
            println("#####$key, $partner")
        }
        return builder.build()
    }
}
