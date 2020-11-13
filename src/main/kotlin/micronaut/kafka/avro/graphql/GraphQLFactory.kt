package micronaut.kafka.avro.graphql


import graphql.GraphQL
import graphql.language.ObjectTypeDefinition
import graphql.schema.idl.*
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import io.micronaut.core.io.ResourceResolver
import micronaut.kafka.avro.config.GraphQLFactoryConfig
import micronaut.kafka.avro.model.Partner
import micronaut.kafka.avro.model.PartnerV1
import micronaut.kafka.avro.model.PartnerV2
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Singleton


@SuppressWarnings("Duplicates")
@Factory
class GraphQLFactory(private val config: GraphQLFactoryConfig) {

    @Bean
    @Singleton
    fun graphQL(resourceResolver: ResourceResolver, vararg fetcherRegistry: Fetcher<*>): GraphQL {

        val schemaParser = SchemaParser()
        val schemaGenerator = SchemaGenerator()
        val typeRegistry = TypeDefinitionRegistry()

        val typeDefinitionRegistry = schemaParser.parse(BufferedReader(InputStreamReader(
                resourceResolver.getResourceAsStream(config.schemaConfigPath).get())))

        typeRegistry.merge(typeDefinitionRegistry)

        config.registeredSchemaTypes.forEach { schemaType ->
            isSchemaRegistered(schemaType, typeDefinitionRegistry, *fetcherRegistry)
        }

        val runtimeWiring = fetcherRegistry
                .fold(RuntimeWiring.newRuntimeWiring()) { typeWiring, f -> typeWiring.type(f.schemaType, f::register) }
                .type(TypeRuntimeWiring.newTypeWiring("Partner").typeResolver {
                    when (it.getObject<Partner>()) {
                        is PartnerV1 -> it.schema.getObjectType("PartnerV1")
                        is PartnerV2 -> it.schema.getObjectType("PartnerV2")
                        else -> null
                    }
                }.build())
                .build()

        val graphQLSchema = schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring)

        return GraphQL.newGraphQL(graphQLSchema).build()
    }

    private fun isSchemaRegistered(schemaType: String, typeDefinitionRegistry: TypeDefinitionRegistry,
                                   vararg registry: Fetcher<*>) {

        val graphqlSchemaDef = typeDefinitionRegistry.types()[schemaType] as ObjectTypeDefinition

        graphqlSchemaDef.fieldDefinitions?.map { fd -> fd.name }?.forEach { endpoint ->

            val registeredEndpoint = registry
                    .filter { f -> f.schemaType == schemaType }
                    .map { f -> f.graphQlEndpoint }

            if (!registeredEndpoint.contains(endpoint)) {
                throw ClassNotFoundException("No implementation was found for $schemaType - $endpoint")
            }
        }
    }
}