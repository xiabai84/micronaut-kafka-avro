package micronaut.kafka.avro.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonTypeInfo(use = JsonTypeInfo.Id.MINIMAL_CLASS, property = "type")
data class Partner(
    @JsonProperty
    val id: String,
    @JsonProperty
    val vorname: String,
    @JsonProperty
    val nachname: String,
    @JsonProperty
    val age: Int?,
    @JsonProperty
    val email: String?
)
