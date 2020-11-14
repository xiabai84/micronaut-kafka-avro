package micronaut.kafka.avro.model

import micronaut.kafka.avro.utils.loadAvroSchema
import org.apache.avro.Schema
import org.apache.avro.generic.GenericData
import org.apache.avro.generic.GenericRecord

data class PartnerV2(
        override val id: String,
        val vorname: String,
        val nachname: String,
        val age: Int? = null // like schema defined
): Partner

fun PartnerV2.toGenericRecord(): GenericRecord {
    val partnerSchema = Schema.Parser()
            .parse(loadAvroSchema(filename = "partner-v2.avsc"))
    val record = GenericData.Record(partnerSchema)

    with (record) {
        put("id", id)
        put("vorname", vorname)
        put("nachname", nachname)
        put("age", age)
    }
    return record
}

fun GenericRecord.toPartnerV2(): PartnerV2 {
    return PartnerV2(
            get("id").toString(),
            get("vorname").toString(),
            get("nachname").toString(),
            get("age") as Int
    )
}