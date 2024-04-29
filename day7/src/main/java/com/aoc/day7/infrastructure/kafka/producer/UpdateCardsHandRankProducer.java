package com.aoc.day7.infrastructure.kafka.producer;

import com.aoc.day7.infrastructure.logger.CustomLogger;
import com.aoc.day7.infrastructure.logger.CustomLoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class UpdateCardsHandRankProducer {
    private final KafkaTemplate<String, Integer> template;
    private final CustomLogger logger;
    private final String topicName;

    public UpdateCardsHandRankProducer(
            KafkaTemplate<String, Integer> template,
            CustomLoggerFactory customLoggerFactory,
            @Value("${topic.total-cards-hand}") String topicName
    ) {
        this.template = template;
        this.logger = customLoggerFactory.createLogger(UpdateCardsHandRankProducer.class);
        this.topicName = topicName;
    }

    public void send(int totalCardsHand) {
        CompletableFuture<SendResult<String, Integer>> future = template.send(topicName, totalCardsHand);
        future.whenComplete((_, ex) -> {
            if (ex != null) {
                logger.error(STR."Problem when send message, ex : \{ex.getMessage()}");
            }
        });
    }
}