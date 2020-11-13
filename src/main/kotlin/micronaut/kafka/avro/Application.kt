package micronaut.kafka.avro

import io.micronaut.runtime.Micronaut

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("micronaut.kafka.avro")
                .mainClass(Application.javaClass)
                .start()
    }
}