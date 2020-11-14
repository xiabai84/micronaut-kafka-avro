# micronaut-kafka-avro
Schema-Evolution by using Kafka, Micronaut and Avro serialization.

Technologies:
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
    id:"1234",
		vorname: "bai",
    nachname: "xia",
    email: "test@test.com"
  }) {
    id
    vorname
    nachname
    email
  }
}
```

Check record in console output...

After that checkout the other branch "v2/spec-record" and send some new record with schema version 2
```
mutation {
  createPartnerV2(partnerV2: {
    id: "12345",
    vorname: "bai",
    nachname: "xia",
    age: 18
  }) {
    id
    vorname
    nachname
    age
  }
}
```

You will see, that both consumer 1 and 2 can handle the data from partner-topic. 

Validate schema version via Confluent Control Center...
