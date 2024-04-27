package com.aoc.day7.core.usecase;

import com.aoc.day7.core.model.CardsHand;
import com.aoc.day7.core.model.CardsHandWithScore;
import org.springframework.stereotype.Service;

@Service
public class MapToCardsHandWithScore {
    public CardsHandWithScore execute(CardsHand cardsHand) {
        return new CardsHandWithScore(
                cardsHand.order(),
                cardsHand.cards(),
                cardsHand.bid(),
                cardsHand.getScore()
        );
    }
}
