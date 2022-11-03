package micronaut.kafka.avro.kafka

import io.micronaut.context.annotation.Factory
import micronaut.kafka.avro.config.ProducerProperty
import micronaut.kafka.avro.config.TopicConfig
import micronaut.kafka.avro.model.Partner
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.Producer
import org.apache.kafka.clients.producer.ProducerRecord
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Singleton


@Factory
class ProducerClient(
        private val topicConfig: TopicConfig,
        private val producerProperty: ProducerProperty
) {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @Singleton
    fun sendToPartnerTopic(partner: Partner): Partner {
        val props = producerProperty.props
        val producer: Producer<CharSequence, Partner> = KafkaProducer(props)

        with (producer) {
            send(ProducerRecord(topicConfig.partnerTopic, partner.id, partner))
            flush()
            close()
            logger.info("$partner")
        }

        return partner
    }
}
