package micronaut.kafka.avro.config

import io.confluent.kafka.serializers.json.KafkaJsonSchemaSerializer
import io.micronaut.context.annotation.Value
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import java.util.*


class ProducerProperty {

    private val env = System.getenv()

    @Value("\${kafka.bootstrap.servers}")
    var bootstrapServers: String = ""

    @Value("\${kafka.schema.registry.url}")
    var schemaRegistryUrl: String = ""

    val props
        get() = Properties().apply {
            put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer::class.java.canonicalName)
            put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaJsonSchemaSerializer::class.java.canonicalName)
            put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, env["BOOTSTRAP_SERVERS"] ?: bootstrapServers)
            put("schema.registry.url", env["SCHEMA_REGISTRY_URL_CONFIG"] ?: schemaRegistryUrl)
        }
}
