package com.aoc.day7.infrastructure.mongo.repository;

import com.aoc.day7.core.model.CardsHand;

public interface InsertCardsHandRepository {
    void insertOne(CardsHand cardsHand);
}