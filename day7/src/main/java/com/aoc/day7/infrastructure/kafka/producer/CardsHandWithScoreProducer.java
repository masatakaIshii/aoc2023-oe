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
public class CardsHandWithScoreProducer {
    private final KafkaTemplate<String, CardsHandEvent> template;
    private final CustomLogger logger;
    private final String topicName;

    public CardsHandWithScoreProducer(
            KafkaTemplate<String, CardsHandEvent> template,
            CustomLoggerFactory factory,
            @Value("${topic.cards-hand}") String topicName) {
        this.template = template;
        this.logger = factory.createLogger(CardsHandWithScoreProducer.class);
        this.topicName = topicName;
    }

    public void send(CardsHand cardsHand) {
        CardsHandEvent cardsHandEvent = new CardsHandEvent(
                cardsHand.order(),
                cardsHand.cards(),
                cardsHand.bid(),
                cardsHand.scores()
        );

        CompletableFuture<SendResult<String, CardsHandEvent>> future = template.send(topicName, UUID.randomUUID().toString(), cardsHandEvent);
        future.whenComplete((_, ex) -> {
            if (ex != null) {
                logger.error(STR."Problem when send message, ex : \{ex.getMessage()}");
            }
        });
    }
}
