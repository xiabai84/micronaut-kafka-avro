package micronaut.kafka.avro.service

import micronaut.kafka.avro.kafka.ProducerClient
import micronaut.kafka.avro.model.Partner
import javax.inject.Singleton

@Singleton
class MutationService(private val producerClient: ProducerClient){

    fun sendPartnerV1(partner: Partner): Partner {
        producerClient.sendToPartnerTopic(partner)
        return partner
    }
}