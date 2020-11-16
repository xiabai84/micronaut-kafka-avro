package micronaut.kafka.avro.model

data class PartnerView(
        val id: String,
        val vorname: String,
        val nachname: String,
        val age: Int?,
        val email: String?
)
