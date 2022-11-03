package micronaut.kafka.avro.kafka

import com.fasterxml.jackson.databind.ObjectMapper
import io.confluent.kafka.schemaregistry.client.CachedSchemaRegistryClient
import io.confluent.kafka.schemaregistry.json.JsonSchemaProvider
import io.confluent.kafka.serializers.json.KafkaJsonSchemaDeserializerConfig
import io.micronaut.context.annotation.Value
import org.apache.kafka.common.serialization.Deserializer
import org.apache.kafka.common.serialization.Serializer
import javax.inject.Singleton

@Singleton
class JsonSchemaSerdes(
    val micronautObjectMapper: ObjectMapper,
    @Value("\${kafka.schema.registry.url}") val schemaRegistryUrl: String = ""
) {

    private val config = mapOf(
        "schema.registry.url" to schemaRegistryUrl
    )

    private val schemaRegistry = CachedSchemaRegistryClient(
        listOf(schemaRegistryUrl),
        1000, // config.getMaxSchemasPerSubject() => config[MAX_SCHEMAS_PER_SUBJECT_CONFIG]
        listOf(JsonSchemaProvider()),
        emptyMap<String,Any>(),
        emptyMap<String,String>()
    )

    fun <T> create(clazz: Class<T>, isKey: Boolean): JsonSchemaSerde<T> {
        val serde = JsonSchemaSerde(schemaRegistry, micronautObjectMapper, clazz)
        serde.configure(config, isKey)
        return serde
    }

    fun <T> value(clazz: Class<T>): JsonSchemaSerde<T> {
        return create(clazz, false)
    }

    fun <T> key(clazz: Class<T>): JsonSchemaSerde<T> {
        return create(clazz, true)
    }
}