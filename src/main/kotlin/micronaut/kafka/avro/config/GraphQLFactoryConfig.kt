package micronaut.kafka.avro.config

import io.micronaut.context.annotation.Value

class GraphQLFactoryConfig {

    @Value("\${graphql-factory-config.classpath}")
    var schemaConfigPath: String = ""

    @Value("\${graphql-factory-config.schema}")
    var registeredSchemaTypes: List<String> = emptyList()
}