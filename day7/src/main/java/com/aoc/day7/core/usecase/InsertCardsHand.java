package com.aoc.day7.core.usecase;

import com.aoc.day7.core.adapter.CardsHandRepository;
import com.aoc.day7.core.model.CardsHandWithScore;
import org.springframework.stereotype.Service;

@Service
public class InsertCardsHand {
    private final CardsHandRepository cardsHandRepository;

    public InsertCardsHand(CardsHandRepository cardsHandRepository) {
        this.cardsHandRepository = cardsHandRepository;
    }

    public void execute(CardsHandWithScore cardsHandWithScore) {
        cardsHandRepository.insertOne(cardsHandWithScore);
    }
}