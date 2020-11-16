package micronaut.kafka.avro.graphql

import graphql.schema.DataFetchingEnvironment
import graphql.schema.idl.TypeRuntimeWiring
import micronaut.kafka.avro.model.PartnerView
import micronaut.kafka.avro.service.QueryService
import javax.inject.Singleton

@Singleton
@SuppressWarnings("Duplicates")
class PartnerV3QueryDataFetcher(private val query: QueryService) : Fetcher<PartnerView?> {

    override val schemaType: String
        get() = "Query"

    override val graphQlEndpoint: String
        get() = "partnerV3"

    override fun getDataFetcher(env: DataFetchingEnvironment): PartnerView? {
        val partnerId = env.getArgument<String?>("partnerId")
        return query.partner(partnerId)
    }

    override fun register(runtimeWiring: TypeRuntimeWiring.Builder): TypeRuntimeWiring.Builder =
            runtimeWiring.dataFetcher(graphQlEndpoint, this::getDataFetcher)
}