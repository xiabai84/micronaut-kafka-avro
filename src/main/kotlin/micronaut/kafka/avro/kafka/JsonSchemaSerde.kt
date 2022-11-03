package micronaut.kafka.avro.kafka

import com.fasterxml.jackson.databind.ObjectMapper
import io.confluent.kafka.schemaregistry.client.SchemaRegistryClient
import org.apache.kafka.common.serialization.Deserializer
import org.apache.kafka.common.serialization.Serde
import org.apache.kafka.common.serialization.Serializer

class JsonSchemaSerde<T>(
    client: SchemaRegistryClient,
    micronautObjectMapper: ObjectMapper,
    clazz: Class<T>
): Serde<T> {
    private val serializer = JsonSchemaSerializer<T>(client, micronautObjectMapper)
    private val deserializer = JsonSchemaDeserializer(client, micronautObjectMapper, clazz)

    override fun serializer(): Serializer<T> {
        return serializer
    }

    override fun deserializer(): Deserializer<T> {
        return deserializer
    }

    override fun configure(serdeConfig: Map<String, *>?, isSerdeForRecordKeys: Boolean) {
        serializer.configure(serdeConfig, isSerdeForRecordKeys)
        deserializer.configure(serdeConfig, isSerdeForRecordKeys)
    }
}