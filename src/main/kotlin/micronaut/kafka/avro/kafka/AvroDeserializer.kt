package micronaut.kafka.avro.kafka

import org.apache.avro.generic.GenericRecord
import org.apache.avro.io.DatumReader
import org.apache.avro.io.Decoder
import org.apache.avro.io.DecoderFactory
import org.apache.avro.specific.SpecificDatumReader
import org.apache.avro.specific.SpecificRecordBase
import org.apache.kafka.common.serialization.Deserializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory


class AvroDeserializer<T : SpecificRecordBase?>(private val targetType: Class<T>) : Deserializer<Any?> {

    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    override fun configure(configs: MutableMap<String, *>?, isKey: Boolean) { }

    override fun deserialize(topic: String, bytes: ByteArray): T? {
        var returnObject: T? = null
        try {
            val datumReader: DatumReader<GenericRecord?> = SpecificDatumReader(
                    targetType.getDeclaredConstructor().newInstance()?.schema
            )
            val decoder: Decoder = DecoderFactory.get().binaryDecoder(bytes, null)
            returnObject = datumReader.read(null, decoder) as T?
        } catch (e: Exception) {
            log.error("Unable to Deserialize bytes[] ", e)
        }
        return returnObject
    }

    override fun close() { }
}