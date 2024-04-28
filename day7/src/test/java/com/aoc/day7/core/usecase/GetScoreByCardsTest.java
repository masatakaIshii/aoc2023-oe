package com.aoc.day7.core.usecase;

import org.junit.jupiter.api.Test;

import static com.aoc.day7.core.usecase.GetScoreByCards.getScore;
import static com.aoc.day7.core.usecase.GetScoreByCards.getScoreWithJokers;
import static org.assertj.core.api.Assertions.assertThat;

class GetScoreByCardsTest {

    @Test
    void when_cards_contain_12345_should_get_10102030405_score() {
        assertThat(getScore("12345")).isEqualTo(10102030405L);
    }

    @Test
    void when_cards_contain_32T3K_should_get_20302100313_score() {
        assertThat(getScore("32T3K")).isEqualTo(20302100313L);
    }

    @Test
    void when_cards_contain_KK677_should_get_31313060707_score() {
        assertThat(getScore("KK677")).isEqualTo(31313060707L);
    }

    @Test
    void when_cards_contain_T55J5_should_get_31005051105_score() {
        assertThat(getScore("T55J5")).isEqualTo(41005051105L);
    }

    @Test
    void when_cards_contain_T55J5_should_get_41005051005_score() {
        assertThat(getScore("T55T5")).isEqualTo(51005051005L);
    }

    @Test
    void when_cards_contain_AAAAA_should_get_51414141414_score() {
        assertThat(getScore("AAAAA")).isEqualTo(71414141414L);
    }

    @Test
    void when_cards_contain_J1234_should_get_20001020304_score() {
        assertThat(getScoreWithJokers("J1234")).isEqualTo(20001020304L);
    }
    @Test
    void when_cards_contain_T55J5_should_get_20001020304_score() {
        assertThat(getScoreWithJokers("T55J5")).isEqualTo(61005050005L);
    }

    @Test
    void when_cards_contain_KTJJT_should_get_61310000010_score() {
        assertThat(getScoreWithJokers("KTJJT")).isEqualTo(61310000010L);
    }

}