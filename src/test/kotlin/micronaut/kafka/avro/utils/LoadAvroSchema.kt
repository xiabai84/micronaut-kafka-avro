package micronaut.kafka.avro.utils

import org.junit.jupiter.api.Test

class LoadAvroSchema {
    @Test
    fun `loadAvroSchema should read avro schema file under resources avro folder`(){
        val str = loadAvroSchema("partner-v3.avsc")
        println(str)
    }
}
