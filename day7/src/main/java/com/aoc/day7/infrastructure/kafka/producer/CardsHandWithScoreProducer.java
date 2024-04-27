package com.aoc.day7.infrastructure.kafka.producer;

import com.aoc.day7.core.model.CardsHandWithScore;
import com.aoc.day7.infrastructure.kafka.model.CardsHandWithScoreEvent;
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
    private final KafkaTemplate<String, CardsHandWithScoreEvent> template;
    private final CustomLogger logger;
    private final String topicName;

    public CardsHandWithScoreProducer(
            KafkaTemplate<String, CardsHandWithScoreEvent> template,
            CustomLoggerFactory factory,
            @Value("${topic.cards-with-score-hand}") String topicName) {
        this.template = template;
        this.logger = factory.createLogger(CardsHandWithScoreProducer.class);
        this.topicName = topicName;
    }

    public void send(CardsHandWithScore cardsHandWithScore) {
        CardsHandWithScoreEvent cardsHandWithScoreEvent = new CardsHandWithScoreEvent(
                cardsHandWithScore.order(),
                cardsHandWithScore.cards(),
                cardsHandWithScore.bid(),
                cardsHandWithScore.scores()
        );

        CompletableFuture<SendResult<String, CardsHandWithScoreEvent>> future = template.send(topicName, UUID.randomUUID().toString(), cardsHandWithScoreEvent);
        future.whenComplete((_, ex) -> {
            if (ex != null) {
                logger.error(STR."Problem when send message, ex : \{ex.getMessage()}");
            }
        });
    }
}
