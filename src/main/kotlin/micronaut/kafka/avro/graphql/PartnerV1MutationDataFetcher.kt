package micronaut.kafka.avro.graphql

import graphql.schema.DataFetchingEnvironment
import graphql.schema.idl.TypeRuntimeWiring
import micronaut.kafka.avro.model.Partner
import micronaut.kafka.avro.model.PartnerV1
import micronaut.kafka.avro.service.MutationService
import javax.inject.Singleton

@Singleton
@SuppressWarnings("Duplicates")
class PartnerV1MutationDataFetcher(private val mutation: MutationService) : Fetcher<Partner> {

    override val schemaType: String
        get() = "Mutation"

    override val graphQlEndpoint: String
        get() = "createPartnerV1"

    override fun getDataFetcher(env: DataFetchingEnvironment): Partner {
        val partnerInput = env.getArgument<Map<String, String?>>("partnerV1")
        val partner = PartnerV1(
                id = partnerInput["id"] ?: "default",
                vorname = partnerInput["vorname"] ?: "default",
                nachname = partnerInput["nachname"] ?: "default",
                email = partnerInput["email"]
        )

        mutation.sendPartnerV1(partner)
        return partner
    }

    override fun register(runtimeWiring: TypeRuntimeWiring.Builder): TypeRuntimeWiring.Builder =
            runtimeWiring.dataFetcher(graphQlEndpoint, this::getDataFetcher)
}