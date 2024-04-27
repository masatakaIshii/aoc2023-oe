package com.aoc.day7.infrastructure.kafka.producer;

import com.aoc.day7.core.model.CardsHand;
import com.aoc.day7.infrastructure.kafka.model.CardsHandSchemaRegistry;
import com.aoc.day7.infrastructure.logger.CustomLogger;
import com.aoc.day7.infrastructure.logger.CustomLoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class CamelCardsProducer {
    private final KafkaTemplate<String, CardsHandSchemaRegistry> template;
    private final CustomLogger logger;
    private final String topicName;

    public CamelCardsProducer(
            KafkaTemplate<String, CardsHandSchemaRegistry> template,
            CustomLoggerFactory loggerFactory,
            @Value("${topic.name}") String topicName) {
        this.template = template;
        logger = loggerFactory.createLogger(CamelCardsProducer.class);
        this.topicName = topicName;
    }

    public void send(CardsHand cardsHand) {
        logger.info("start producer");
        CardsHandSchemaRegistry cardsHandSchemaRegistry = new CardsHandSchemaRegistry(
                cardsHand.getOrder(),
                cardsHand.getCards(),
                cardsHand.getBid()
        );
        CompletableFuture<SendResult<String, CardsHandSchemaRegistry>> send = template.send(topicName, UUID.randomUUID().toString(), cardsHandSchemaRegistry);
        send.whenComplete((result, ex) -> {
            if (ex == null) {
                logger.info(STR."cards hand data send :\{result.getProducerRecord().value()}");
            } else {
                logger.error(STR."Problem when send message, ex : \{ex.getMessage()}");
            }
        });

    }
}
