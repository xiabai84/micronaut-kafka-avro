package micronaut.kafka.avro.service

import io.micronaut.configuration.kafka.streams.InteractiveQueryService
import micronaut.kafka.avro.config.StoreConfig
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
                PartnerView(
                        id = partner.id.toString(),
                        vorname = partner.vorname.toString(),
                        nachname = partner.nachname.toString(),
                        age = partner.age,
                        email = partner.email?.toString()
                )
            }
            .orElse(null)

}