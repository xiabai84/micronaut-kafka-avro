package micronaut.kafka.avro.utils

fun loadAvroSchema(filename: String) = {}::class.java.getResource("/avro/$filename").readText()