package com.aoc.day7.infrastructure.kafka.producer;

import com.aoc.day7.core.model.CardsHand;
import com.aoc.day7.infrastructure.kafka.model.CardsHandEvent;
import com.aoc.day7.infrastructure.logger.CustomLogger;
import com.aoc.day7.infrastructure.logger.CustomLoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class CardsHandProducer {
    private final KafkaTemplate<String, CardsHandEvent> template;
    private final CustomLogger logger;
    private final String topicName;

    public CardsHandProducer(
            KafkaTemplate<String, CardsHandEvent> template,
            CustomLoggerFactory loggerFactory,
            @Value("${topic.cards-hand}") String topicName) {
        this.template = template;
        logger = loggerFactory.createLogger(CardsHandProducer.class);
        this.topicName = topicName;
    }

    public void send(CardsHand cardsHand) {
        CardsHandEvent cardsHandEvent = new CardsHandEvent(
                cardsHand.order(),
                cardsHand.cards(),
                cardsHand.bid()
        );
        CompletableFuture<SendResult<String, CardsHandEvent>> send = template.send(topicName, UUID.randomUUID().toString(), cardsHandEvent);
        send.whenComplete((_, ex) -> {
            if (ex != null) {
                logger.error(STR."Problem when send message, ex : \{ex.getMessage()}");
            }
        });

    }
}
