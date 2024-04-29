# Day 7

This over-engineering project concerns this [advent of code](https://adventofcode.com/2023/day/7).

The tools that are used :
- Java 21
- Maven
- Spring boot web
- Spring kafka
- Spring boot aop
- Kafka schema registry with avro
- MongoDB
- Tests containers for tests e2e with dockerized Kafka and MongoDB

The process to solve this problem contains few steps :
### 1. Insert cards hand
In [CamelCardsController](src/main/java/com/aoc/day7/infrastructure/controller/CamelCardsController.java) read [jdd.txt](src/main/resources/jdd.txt) file and for each line that represents cards hand :
- [get score](src/main/java/com/aoc/day7/core/usecase/GetScoreByCards.java) of each cards hand 
- with the line and the score, create [cards hand](src/main/java/com/aoc/day7/core/model/CardsHand.java)
- send each cards hand as message kafka with a [producer](src/main/java/com/aoc/day7/infrastructure/kafka/producer/CardsHandWithScoreProducer.java)
- a [consumer](src/main/java/com/aoc/day7/infrastructure/kafka/consumer/CardsHandWithScoreConsumer.java) listens each message of cards hand to insert in mongo collection

At the end of file a kafka message contains total of lines is sent by this [producer](src/main/java/com/aoc/day7/infrastructure/kafka/producer/UpdateCardsHandRankProducer.java).

### 2. Set rank to each cards hand
A [consumer](src/main/java/com/aoc/day7/infrastructure/kafka/consumer/UpdateCardsHandRankConsumer.java) listen to the kafka messages that contains total of lines and check if the collection contains same count :
- if the collection is less then it throw exception and the consumer retry to listen the same topic after few times
- if the collection contains same total then the program start to set rank to each cards hand
- the rank of cards hand is set per batch of 100 by pagination

### 3. Get result
After the ranks are set, the [total](src/main/java/com/aoc/day7/infrastructure/mongo/repository/MongoDBRepository.java?plain=65) of each bid's card multiply by its rank is computed.
