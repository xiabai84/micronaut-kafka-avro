package micronaut.kafka.avro.service

import io.micronaut.configuration.kafka.streams.InteractiveQueryService
import micronaut.kafka.avro.config.StoreConfig
import micronaut.kafka.avro.model.JuristicPerson
import micronaut.kafka.avro.model.NaturalPerson
import micronaut.kafka.avro.model.Partner
import micronaut.kafka.avro.model.PartnerView
import org.apache.kafka.streams.state.QueryableStoreTypes
import javax.inject.Singleton

@Singleton
class QueryService(
        private val query: InteractiveQueryService,
        private val stores: StoreConfig,
) {

    fun partner(id: String): PartnerView? = query
            .getQueryableStore(stores.partnerStore, QueryableStoreTypes.keyValueStore<String, Partner>())
            .map { v ->
                val partner = v[id]

                when(partner?.specInfo) {

                    is NaturalPerson -> PartnerView(
                            id = partner.partnerId.toString(),
                            vorname = (partner.specInfo as NaturalPerson).firstName.toString(),
                            nachname = (partner.specInfo as NaturalPerson).secondName.toString(),
                            name = null
                    )

                    is JuristicPerson -> PartnerView(
                            id = partner.partnerId.toString(),
                            vorname = null,
                            nachname = null,
                            name = (partner.specInfo as NaturalPerson).secondName.toString()
                    )

                    else -> null
                }
            }
            .orElse(null)

}