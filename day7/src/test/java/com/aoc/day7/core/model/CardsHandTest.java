package com.aoc.day7.core.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CardsHandTest {

    @Test
    void when_cards_contain_12345_should_get_10102030405_score() {
        CardsHand cardsHand = new CardsHand(1L, "12345", 123L);

        assertThat(cardsHand.getScore()).isEqualTo(10102030405L);
    }

    @Test
    void when_cards_contain_32T3K_should_get_20302100313_score() {
        CardsHand cardsHand = new CardsHand(1L, "32T3K", 123L);

        assertThat(cardsHand.getScore()).isEqualTo(20302100313L);
    }

    @Test
    void when_cards_contain_KK677_should_get_31313060707_score() {
        CardsHand cardsHand = new CardsHand(1L, "KK677", 123L);

        assertThat(cardsHand.getScore()).isEqualTo(31313060707L);
    }

    @Test
    void when_cards_contain_T55J5_should_get_31005051105_score() {
        CardsHand cardsHand = new CardsHand(1L, "T55J5", 123L);

        assertThat(cardsHand.getScore()).isEqualTo(41005051105L);
    }

    @Test
    void when_cards_contain_T55J5_should_get_41005051005_score() {
        CardsHand cardsHand = new CardsHand(1L, "T55T5", 123L);

        assertThat(cardsHand.getScore()).isEqualTo(51005051005L);
    }

    @Test
    void when_cards_contain_AAAAA_should_get_51414141414_score() {
        CardsHand cardsHand = new CardsHand(1L, "AAAAA", 123L);

        assertThat(cardsHand.getScore()).isEqualTo(71414141414L);
    }
}