package micronaut.kafka.avro.model

data class Partner(
    val id: String,
    val vorname: String,
    val nachname: String,
    val age: Int?,
    val email: String?
)
