package com.aoc.day7.core.usecase;

import com.aoc.day7.core.adapter.CardsHandRepository;
import com.aoc.day7.core.model.CardsHand;
import org.springframework.stereotype.Service;

@Service
public class InsertCardsHand {
    private final CardsHandRepository cardsHandRepository;

    public InsertCardsHand(CardsHandRepository cardsHandRepository) {
        this.cardsHandRepository = cardsHandRepository;
    }

    public void execute(CardsHand cardsHand) {
        cardsHandRepository.insertOne(cardsHand);
    }
}
