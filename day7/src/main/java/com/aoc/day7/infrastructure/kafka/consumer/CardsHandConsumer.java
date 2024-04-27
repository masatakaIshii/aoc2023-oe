package com.aoc.day7.infrastructure.kafka.consumer;

import com.aoc.day7.core.model.CardsHand;
import com.aoc.day7.core.model.CardsHandWithScore;
import com.aoc.day7.core.usecase.MapToCardsHandWithScore;
import com.aoc.day7.infrastructure.kafka.model.CardsHandEvent;
import com.aoc.day7.infrastructure.kafka.producer.CardsHandWithScoreProducer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CardsHandConsumer {

    private final MapToCardsHandWithScore mapToCardsHandWithScore;
    private final CardsHandWithScoreProducer cardsHandWithScoreProducer;

    public CardsHandConsumer(MapToCardsHandWithScore mapToCardsHandWithScore, CardsHandWithScoreProducer cardsHandWithScoreProducer) {
        this.mapToCardsHandWithScore = mapToCardsHandWithScore;
        this.cardsHandWithScoreProducer = cardsHandWithScoreProducer;
    }

    @KafkaListener(topics = "${topic.cards-hand}")
    void consume(ConsumerRecord<String, CardsHandEvent> consumerRecord) {
        CardsHandEvent cardsHandEvent = consumerRecord.value();
        CardsHand cardsHand = new CardsHand(cardsHandEvent.getOrder(), cardsHandEvent.getCards().toString(), cardsHandEvent.getBid());

        CardsHandWithScore cardsHandWithScore = mapToCardsHandWithScore.execute(cardsHand);

        cardsHandWithScoreProducer.send(cardsHandWithScore);
    }

}
