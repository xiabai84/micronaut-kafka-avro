package micronaut.kafka.avro.model

import micronaut.kafka.avro.utils.loadAvroSchema
import org.apache.avro.Schema
import org.apache.avro.generic.GenericData
import org.apache.avro.generic.GenericRecord
import org.apache.avro.generic.GenericRecordBuilder
import org.apache.avro.specific.SpecificRecord
import org.apache.avro.specific.SpecificRecordBuilderBase


data class PartnerV1(
        override val id: String,
        val vorname: String,
        val nachname: String,
        val email: String? = null // like schema of v1 defined
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

fun GenericRecord.toPartnerV1(): PartnerV1 {
    println("aktuelle $schema")
    return PartnerV1(
        get("id").toString(),
        get("vorname").toString(),
        get("nachname").toString(),
        get("email")?.toString()
    )
}
