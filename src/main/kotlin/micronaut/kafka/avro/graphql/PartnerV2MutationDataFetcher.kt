package micronaut.kafka.avro.graphql

import graphql.schema.DataFetchingEnvironment
import graphql.schema.idl.TypeRuntimeWiring
import micronaut.kafka.avro.model.Partner
import micronaut.kafka.avro.model.PartnerV2
import micronaut.kafka.avro.service.MutationService
import javax.inject.Singleton

@Singleton
@SuppressWarnings("Duplicates")
class PartnerV2MutationDataFetcher(private val mutation: MutationService) : Fetcher<Partner> {

    override val schemaType: String
        get() = "Mutation"

    override val graphQlEndpoint: String
        get() = "createPartnerV2"

    override fun getDataFetcher(env: DataFetchingEnvironment): Partner {
        val partnerInput = env.getArgument<Map<String, *>>("partnerV2")

        val partner = PartnerV2(
                id = (partnerInput["id"] ?: "default") as String,
                vorname = (partnerInput["vorname"] ?: "default") as String,
                nachname = (partnerInput["nachname"] ?: "default") as String,
                age = partnerInput["age"] as Int?
        )

        mutation.sendPartnerV2(partner)
        return partner
    }

    override fun register(runtimeWiring: TypeRuntimeWiring.Builder): TypeRuntimeWiring.Builder =
            runtimeWiring.dataFetcher(graphQlEndpoint, this::getDataFetcher)
}
