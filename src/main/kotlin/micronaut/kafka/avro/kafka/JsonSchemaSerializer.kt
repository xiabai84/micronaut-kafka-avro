package micronaut.kafka.avro.kafka

import com.fasterxml.jackson.databind.ObjectMapper
import io.confluent.kafka.schemaregistry.client.SchemaRegistryClient
import io.confluent.kafka.serializers.json.KafkaJsonSchemaSerializer
import io.confluent.kafka.serializers.json.KafkaJsonSchemaSerializerConfig

class JsonSchemaSerializer<T>(
    client: SchemaRegistryClient,
    private val micronautObjectMapper: ObjectMapper
): KafkaJsonSchemaSerializer<T>(client) {
    override fun configure(config: KafkaJsonSchemaSerializerConfig) {
        super.configure(config)
        objectMapper = micronautObjectMapper
    }
}