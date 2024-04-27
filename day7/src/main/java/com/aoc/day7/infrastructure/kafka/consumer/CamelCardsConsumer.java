package com.aoc.day7.infrastructure.kafka.consumer;

import com.aoc.day7.core.model.CardsHand;
import com.aoc.day7.core.usecase.InsertCardsHand;
import com.aoc.day7.infrastructure.kafka.model.CardsHandSchemaRegistry;
import com.aoc.day7.infrastructure.logger.CustomLogger;
import com.aoc.day7.infrastructure.logger.CustomLoggerFactory;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CamelCardsConsumer {

    private final CustomLogger log;
    private final InsertCardsHand insertCardsHand;

    public CamelCardsConsumer(CustomLoggerFactory customLoggerFactory, InsertCardsHand insertCardsHand) {
        log = customLoggerFactory.createLogger(CamelCardsConsumer.class);
        this.insertCardsHand = insertCardsHand;
    }

    @KafkaListener(topics = "${topic.name}")
    void consume(ConsumerRecord<String, CardsHandSchemaRegistry> consumerRecord) {
        String key = consumerRecord.key();
        CardsHandSchemaRegistry cardsHandSchemaRegistry = consumerRecord.value();

        log.info(STR."Avro message received fro key \{key} value : \{cardsHandSchemaRegistry}");

        CardsHand cardsHand = new CardsHand(cardsHandSchemaRegistry.getOrder(), cardsHandSchemaRegistry.getCards().toString(), cardsHandSchemaRegistry.getBid());
        insertCardsHand.execute(cardsHand);
    }

}
