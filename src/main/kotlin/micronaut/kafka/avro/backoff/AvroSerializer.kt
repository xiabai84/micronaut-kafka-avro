package micronaut.kafka.avro.backoff

import org.apache.avro.generic.GenericDatumWriter
import org.apache.avro.generic.GenericRecord
import org.apache.avro.io.DatumWriter
import org.apache.avro.io.EncoderFactory
import org.apache.kafka.common.serialization.Serializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.ByteArrayOutputStream


class AvroSerializer<T : GenericRecord?> : Serializer<T> {

    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    override fun configure(configs: Map<String?, *>?, isKey: Boolean) { }

    override fun serialize(topic: String?, payload: T?): ByteArray? {
        var bytes: ByteArray? = null
        try {
            if (payload != null) {
                val byteArrayOutputStream = ByteArrayOutputStream()
                val binaryEncoder = EncoderFactory.get().binaryEncoder(byteArrayOutputStream, null)
                val datumWriter: DatumWriter<GenericRecord> = GenericDatumWriter(payload.schema)
                datumWriter.write(payload, binaryEncoder)
                binaryEncoder.flush()
                byteArrayOutputStream.close()
                bytes = byteArrayOutputStream.toByteArray()
            }
        } catch (e: Exception) {
            log.error("Unable to serialize payload ", e)
        }
        return bytes
    }

    override fun close() { }
}