package micronaut.kafka.avro.kafka

import com.fasterxml.jackson.databind.ObjectMapper
import io.micronaut.context.annotation.Factory
import io.micronaut.context.annotation.Value
import micronaut.kafka.avro.config.ProducerProperty
import micronaut.kafka.avro.config.TopicConfig
import micronaut.kafka.avro.model.Partner
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.Producer
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringSerializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Singleton


@Singleton
class ProducerClient(
    private val topicConfig: TopicConfig,
    private val producerProperty: ProducerProperty,
    private val jsonSchemaSerdes: JsonSchemaSerdes
) {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    fun sendToPartnerTopic(partner: Partner): Partner {
        val props = producerProperty.props

        val producer = KafkaProducer(
            props,
            StringSerializer(),
            jsonSchemaSerdes.value(Partner::class.java).serializer()
        )

        with (producer) {
            send(ProducerRecord(topicConfig.partnerTopic, partner.id, partner))
            flush()
            close()
            logger.info("$partner")
        }

        return partner
    }
}
