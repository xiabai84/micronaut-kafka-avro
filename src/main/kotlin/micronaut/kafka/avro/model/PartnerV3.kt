package micronaut.kafka.avro.model

import micronaut.kafka.avro.utils.loadAvroSchema
import org.apache.avro.Schema
import org.apache.avro.generic.GenericData
import org.apache.avro.generic.GenericRecord

data class PartnerV3(
        override val id: String,
        val vorname: String,
        val nachname: String,
        val age: Int? = null,
        val email: String? = null

): Partner

fun PartnerV3.toGenericRecord(): GenericRecord {
    val partnerSchema = Schema.Parser()
            .parse(loadAvroSchema(filename = "partner-v3.avsc"))
    val record = GenericData.Record(partnerSchema)

    with (record) {
        put("id", id)
        put("vorname", vorname)
        put("nachname", nachname)
        put("age", age)
        put("email", email)
    }
    return record
}

fun GenericRecord.toPartnerV3(): PartnerV3 {
    return PartnerV3(
        get("id").toString(),
        get("vorname").toString(),
        get("nachname").toString(),
        get("age") as Int,
        get("email").toString()
    )
}
