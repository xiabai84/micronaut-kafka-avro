package micronaut.kafka.avro.kafka

import com.fasterxml.jackson.databind.ObjectMapper
import io.confluent.kafka.schemaregistry.client.SchemaRegistryClient
import io.confluent.kafka.serializers.json.KafkaJsonSchemaDeserializer
import io.confluent.kafka.serializers.json.KafkaJsonSchemaDeserializerConfig

class JsonSchemaDeserializer<T>(
    client: SchemaRegistryClient,
    private val micronautObjectMapper: ObjectMapper,
    private val clazz: Class<T>
): KafkaJsonSchemaDeserializer<T>(client) {
    override fun configure(config: KafkaJsonSchemaDeserializerConfig?, isKey: Boolean) {
        super.configure(config, isKey)
        type = clazz
        objectMapper = micronautObjectMapper
    }

    override fun deserialize(ignored: String?, bytes: ByteArray?): T {
        println("bin drin")
        val result = super.deserialize(ignored, bytes)
        println(" got ${result}")
        return result
    }
}