# Day 7

This over-engineering project concerns this [puzzle](https://adventofcode.com/2023/day/7).

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
[The controller](src/main/java/com/aoc/day7/infrastructure/controller/CamelCardsController.java) read [the input file](src/main/resources/input.txt) and for each line that represents cards hand :
- it [gets score](src/main/java/com/aoc/day7/core/usecase/GetScoreByCards.java) of each cards hand
- it creates [cards hand](src/main/java/com/aoc/day7/core/model/CardsHand.java) with the line and the score
- it sends each cards hand as message kafka with a [producer](src/main/java/com/aoc/day7/infrastructure/kafka/producer/CardsHandWithScoreProducer.java)
- a [consumer](src/main/java/com/aoc/day7/infrastructure/kafka/consumer/CardsHandWithScoreConsumer.java) listens each message of cards hand to insert in mongo collection

At the end of file a kafka message contains total of lines is sent by this [producer](src/main/java/com/aoc/day7/infrastructure/kafka/producer/UpdateCardsHandRankProducer.java).

### 2. Set rank to each cards hand
A [consumer](src/main/java/com/aoc/day7/infrastructure/kafka/consumer/UpdateCardsHandRankConsumer.java) listen to the kafka messages that contains total of lines and check if the collection contains same count :
- if the collection is less then it throw exception and the consumer retry to listen the same topic after few times
- if the collection contains same total then the program start to set rank to each cards hand
- the rank of cards hand is set per batch of 100 by pagination

### 3. Get result
After the ranks are set, the [total](src/main/java/com/aoc/day7/infrastructure/mongo/repository/MongoDBRepository.java?plain=65) of each bid's card multiply by its rank is computed.

## Pros and cons compare to a project without sophisticated tools
The pros and cons are based this oe project and [this simple one without sophisticated tools](https://github.com/masatakaIshii/aoc2023).

### Processing a larger file
Maybe the only pro for this project compare to the other one :
- Each line of the input size is treated by BufferedReader and not get directly the whole data of file.
- Each line is map to an object that is persistent
- Each update of each object is made directly in the database
- The computation of the result is a mongo request

But for this Advent of Code, this advantage is useless...
This is the only pro that I found, but there are too much cons to write in a README. I put one of the most interesting con that is process time.

### Process time
The time that took this application to give a result

| Puzzle      | Time(ms)    |
| ----------- | ----------- |
| 1st         | 7443        |
| 2nd         | 6880        |

I solved the same puzzle without those tools with  and the time is :

| Puzzle      | Time(ms)    |
| ----------- | ----------- |
| 1st         | 253         |
| 2nd         | 210         |

So about time the oe project is very bad compare to the simple one.

### Conclusion
I had lot of fun to do and to debug also (I had bugs and it took little more time to investigate for this larger project).