package micronaut.kafka.avro.service

import micronaut.kafka.avro.kafka.ProducerClient
import micronaut.kafka.avro.model.PartnerV1
import micronaut.kafka.avro.model.PartnerV2
import javax.inject.Singleton

@Singleton
class MutationService(private val producerClient: ProducerClient){

    fun sendPartnerV1(partnerV1: PartnerV1): PartnerV1 {
        producerClient.sendToPartnerTopic(partnerV1)
        return partnerV1
    }

    fun sendPartnerV2(partnerV2: PartnerV2): PartnerV2 {
        producerClient.sendToPartnerTopic(partnerV2)
        return partnerV2
    }
}