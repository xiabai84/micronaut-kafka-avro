package micronaut.kafka.avro.config

import io.micronaut.context.annotation.ConfigurationProperties

@ConfigurationProperties("topics")
class TopicConfig {
    lateinit var partnerTopic: String
}