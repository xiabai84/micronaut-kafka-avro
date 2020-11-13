package micronaut.kafka.avro.model

import org.junit.jupiter.api.Test

class PartnerV1Tests {

    @Test
    fun `it should convert to generic record, if partner data class matches avro schema`() {
        val partnerRecord = PartnerV1(
                id = "123",
                vorname = "default-vorname",
                nachname = "default-nachname",
                email = "test@test.com"
        ).toGenericRecord()

        println(partnerRecord)
    }

    @Test
    fun `it should return partner, if generic data of partner if given`() {
        val partnerRecord = PartnerV1(
                id = "123",
                vorname = "default-vorname",
                nachname = "default-nachname",
                email = "test@test.com"
        ).toGenericRecord()

        val partner = partnerRecord.toPartnerV1()
        println(partner)
    }
}