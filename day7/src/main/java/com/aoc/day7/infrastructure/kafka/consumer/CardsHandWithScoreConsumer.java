package com.aoc.day7.infrastructure.kafka.consumer;

import com.aoc.day7.infrastructure.mongo.repository.InsertCardsHandRepository;
import com.aoc.day7.core.model.CardsHand;
import com.aoc.day7.infrastructure.kafka.model.CardsHandEvent;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CardsHandWithScoreConsumer {
    private final InsertCardsHandRepository insertCardsHandRepository;

    public CardsHandWithScoreConsumer(InsertCardsHandRepository insertCardsHandRepository) {
        this.insertCardsHandRepository = insertCardsHandRepository;
    }

    @KafkaListener(topics = "${topic.cards-hand}")
    void consume(ConsumerRecord<String, CardsHandEvent> consumerRecord) {
        CardsHandEvent value = consumerRecord.value();
        insertCardsHandRepository.insertOne(new CardsHand(value.getOrder(), value.getCards().toString(), value.getBid(), value.getScore()));
    }
}