package micronaut.kafka.avro.graphql

import graphql.schema.DataFetchingEnvironment
import graphql.schema.idl.TypeRuntimeWiring
import micronaut.kafka.avro.model.Partner
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
        val partner = Partner.newBuilder()
                .setId(partnerInput["id"]  as String)
                .setVorname(partnerInput["vorname"]  as String)
                .setNachname(partnerInput["nachname"] as String)
                .setAge(partnerInput["age"] as Int?)
                .build()

        mutation.sendPartner(partner)
        return partner
    }

    override fun register(runtimeWiring: TypeRuntimeWiring.Builder): TypeRuntimeWiring.Builder =
            runtimeWiring.dataFetcher(graphQlEndpoint, this::getDataFetcher)
}