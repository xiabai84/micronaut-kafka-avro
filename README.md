# micronaut-kafka-avro
Schema-Evolution by using Kafka, Micronaut and Avro serialization.

## Scope:
* Creating auto generated SpecificRecord class using gradle-avro-plugin and Avro file
* Producer can send Avro serialized data with different schema version
* Consumer can use auto generated class consume all kinds of predefined schema without breaking
* Evaluating polymorphism and inheritance with Avro 

## Technologies:
* Kotlin 1.4.0
* Confluent Platform 6.0.0
* Micronaut 2.1.2
* Avro 1.10.0
* gradle-avro-plugin 0.20.0

## Schema Evolution via Specific Record

Step 1 start Confluent Platform:
```
$ confluent start
```

Step 2 prepare topics for kafka streams:
```
$ ./createTopics topics.txt
```

Step 3 use gradle-avro-plug to generate Java Pojo class, which is based on avro schema file:
```
# check out branch, which has schema version 1
$ git checkout v1/spec-record  

# compile java pojo class for producer
$ gradle clean build   
```

Step 4 start backend service
```
$ gradle run
```

Step 5 send some record via GraphQl interface

open graphiql interface in broswer http://localhsot:8080/graphiql and send record with graphql mutation
```
mutation{
  createPartnerV1(partnerV1: {
    {
      partnerId: "123",
      lastEventId: "test-event-id",
      type: "NaturalPerson",
      firstName: "bai",
      secondName: "xia",
      birthDay: "1984-04-11",
      telephone: "7891234",
      email: "test@test.com"
    })
  {
    partnerId
    lastEventId
  }
}
```

Check record in console output...

After that checkout the other branch "v2/spec-record" and send some new record with schema version 2
```
mutation {
  createPartnerV2(partnerV2:
    {
      partnerId: "123",
      lastEventId: "test-event-id",
      type: "NaturalPerson",
      firstName: "bai",
      secondName: "xia",
      birthDay: "1984-04-11",
      telephone: "7891234",
      email: "test@test.com",
      age: 18
    })
  {
    partnerId
    lastEventId
  }
}
```

After that, switch to main branch. There is a stream-app, which uses the current schema to consume data from partner-topic.

You will see, this stream deserializer can handle all schema versions. 

