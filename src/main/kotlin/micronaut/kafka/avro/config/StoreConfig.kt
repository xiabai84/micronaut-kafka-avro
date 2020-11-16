package micronaut.kafka.avro.config

import io.micronaut.context.annotation.ConfigurationProperties

@ConfigurationProperties("stores")
class StoreConfig {
    lateinit var partnerStore: String
}