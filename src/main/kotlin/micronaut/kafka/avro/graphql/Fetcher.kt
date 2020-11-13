package micronaut.kafka.avro.graphql

import graphql.schema.DataFetchingEnvironment
import graphql.schema.idl.TypeRuntimeWiring

interface Fetcher<out T> {
    val schemaType: String
    val graphQlEndpoint: String
    fun getDataFetcher(env: DataFetchingEnvironment): T
    fun register(runtimeWiring: TypeRuntimeWiring.Builder): TypeRuntimeWiring.Builder
}