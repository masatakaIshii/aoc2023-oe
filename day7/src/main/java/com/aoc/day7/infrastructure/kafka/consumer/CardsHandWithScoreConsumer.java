package com.aoc.day7.infrastructure.kafka.consumer;

import com.aoc.day7.core.model.CardsHandWithScore;
import com.aoc.day7.core.usecase.InsertCardsHand;
import com.aoc.day7.infrastructure.kafka.model.CardsHandWithScoreEvent;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CardsHandWithScoreConsumer {
    private final InsertCardsHand insertCardsHand;

    public CardsHandWithScoreConsumer(InsertCardsHand insertCardsHand) {
        this.insertCardsHand = insertCardsHand;
    }

    @KafkaListener(topics = "${topic.cards-with-score-hand}")
    void consume(ConsumerRecord<String, CardsHandWithScoreEvent> consumerRecord) {
        CardsHandWithScoreEvent value = consumerRecord.value();
        insertCardsHand.execute(new CardsHandWithScore(value.getOrder(), value.getCards().toString(), value.getBid(), value.getScore()));
    }
}