package com.aoc.day7.core.adapter;

import com.aoc.day7.core.model.CardsHandWithScore;

public interface CardsHandRepository {
    void insertOne(CardsHandWithScore cardsHandWithScore);
}