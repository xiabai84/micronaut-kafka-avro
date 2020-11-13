package micronaut.kafka.avro.model

import micronaut.kafka.avro.utils.loadAvroSchema
import org.apache.avro.Schema
import org.apache.avro.generic.GenericData
import org.apache.avro.generic.GenericRecord


data class PartnerV1(
        override val id: String,
        val vorname: String,
        val nachname: String,
        val email: String?
): Partner

fun PartnerV1.toGenericRecord(): GenericRecord {
    val partnerSchema = Schema.Parser()
            .parse(loadAvroSchema(filename = "partner-v1.avsc"))
    val record = GenericData.Record(partnerSchema)

    with (record) {
        put("id", id)
        put("vorname", vorname)
        put("nachname", nachname)
        put("email", email)
    }

    return record
}

fun GenericRecord.toPartner(): PartnerV1 {
    return PartnerV1(
        get("id") as String,
        get("vorname") as String,
        get("nachname") as String,
        get("email") as String
    )
}
