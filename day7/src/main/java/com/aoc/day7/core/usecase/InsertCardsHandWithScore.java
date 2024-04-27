package com.aoc.day7.core.usecase;

import com.aoc.day7.core.adapter.CardsHandWithScoreRepository;
import com.aoc.day7.core.model.CardsHandWithScore;
import org.springframework.stereotype.Service;

@Service
public class InsertCardsHandWithScore {
    private final CardsHandWithScoreRepository cardsHandWithScoreRepository;

    public InsertCardsHandWithScore(CardsHandWithScoreRepository cardsHandWithScoreRepository) {
        this.cardsHandWithScoreRepository = cardsHandWithScoreRepository;
    }

    public void execute(CardsHandWithScore cardsHandWithScore) {
        cardsHandWithScoreRepository.insertOne(cardsHandWithScore);
    }
}
