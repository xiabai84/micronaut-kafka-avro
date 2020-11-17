package micronaut.kafka.avro.graphql

import graphql.schema.DataFetchingEnvironment
import graphql.schema.idl.TypeRuntimeWiring
import micronaut.kafka.avro.model.JuristicPerson
import micronaut.kafka.avro.model.NaturalPerson
import micronaut.kafka.avro.model.Partner
import micronaut.kafka.avro.model.PartnerInfo
import micronaut.kafka.avro.service.MutationService
import javax.inject.Singleton

@Singleton
@SuppressWarnings("Duplicates")
class PartnerV1MutationDataFetcher(
        private val mutation: MutationService
) : Fetcher<Partner> {

    override val schemaType: String
        get() = "Mutation"

    override val graphQlEndpoint: String
        get() = "createPartnerV1"

    override fun getDataFetcher(env: DataFetchingEnvironment): Partner {

        val partnerInput = env.getArgument<Map<String, *>>("partnerV1")

        val personSpec = when (partnerInput["type"] as String?) {

            "NaturalPerson" -> NaturalPerson.newBuilder()
                                .setType(partnerInput["type"] as String)
                                .setFirstName(partnerInput["firstName"] as String?)
                                .setSecondName(partnerInput["secondName"] as String?)
                                .setBirthday(partnerInput["birthDay"] as String?)
                                .build()

            "JuristicPerson" -> JuristicPerson.newBuilder()
                                    .setType(partnerInput["type"] as String)
                                    .setName(partnerInput["name"] as String)
                                    .build()

            else -> null
        }

        val partnerInfo = PartnerInfo.newBuilder()
                .setPartnerId(partnerInput["partnerId"] as String)
                .setTelephone(partnerInput["telephone"] as String?)
                .setEmail(partnerInput["email"] as String?)
                .build()

        val partner = Partner.newBuilder()
                .setPartnerId(partnerInput["partnerId"] as String)
                .setLastEventId(partnerInput["lastEventId"] as String?)
                .setPartnerInfo(partnerInfo)
                .setSpecInfo(personSpec)
                .build()

        mutation.sendPartner(partner)

        return partner
    }

    override fun register(runtimeWiring: TypeRuntimeWiring.Builder): TypeRuntimeWiring.Builder =
            runtimeWiring.dataFetcher(graphQlEndpoint, this::getDataFetcher)
}