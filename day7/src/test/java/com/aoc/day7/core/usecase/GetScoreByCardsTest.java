package com.aoc.day7.core.usecase;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.aoc.day7.core.usecase.GetScoreByCards.getScore;
import static com.aoc.day7.core.usecase.GetScoreByCards.getScoreWithJokers;
import static org.assertj.core.api.Assertions.assertThat;

class GetScoreByCardsTest {

    @Nested
    class GetScore {
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
        void when_cards_contain_T55T5_should_get_41005051005_score() {
            assertThat(getScore("T55T5")).isEqualTo(51005051005L);
        }

        @Test
        void when_cards_contain_AAAAA_should_get_51414141414_score() {
            assertThat(getScore("AAAAA")).isEqualTo(71414141414L);
        }
    }

    @Nested
    class GetScoreWithJokers {

        @Test
        void when_cards_contain_ATAAT_should_get_41410141406_score() {
            assertThat(getScoreWithJokers("ATAA6")).isEqualTo(41410141406L);
        }

        @Test
        void when_cards_contain_ATAAT_should_get_51410141410_score() {
            assertThat(getScoreWithJokers("ATAAT")).isEqualTo(51410141410L);
        }

        @Test
        void when_cards_contain_AAAAA_should_get_71414141414_score() {
            assertThat(getScoreWithJokers("AAAAA")).isEqualTo(71414141414L);
        }

        @Test
        void when_cards_contain_K7J84_should_get_21307010804_score() {
            assertThat(getScoreWithJokers("K7J84")).isEqualTo(21307010804L);
        }

        @Test
        void when_cards_contain_KK677_should_get_31313060707_score() {
            assertThat(getScoreWithJokers("KK677")).isEqualTo(31313060707L);
        }

        @Test
        void when_cards_contain_KTJKA_should_get_41310011310_score() {
            assertThat(getScoreWithJokers("KTJKA")).isEqualTo(41310011314L);
        }

        @Test
        void when_cards_contain_ATJAT_should_get_51410011410_score() {
            assertThat(getScoreWithJokers("ATJAT")).isEqualTo(51410011410L);
        }

        @Test
        void when_cards_contain_T55J5_should_get_20001020304_score() {
            assertThat(getScoreWithJokers("T55J5")).isEqualTo(61005050105L);
        }

        @Test
        void when_cards_contain_J2222_should_get_70102020202_score() {
            assertThat(getScoreWithJokers("J2222")).isEqualTo(70102020202L);
        }

        @Test
        void when_cards_contain_J2J34_should_get_20001020304_score() {
            assertThat(getScoreWithJokers("J2J34")).isEqualTo(40102010304L);
        }

        @Test
        void when_cards_contain_KTJJT_should_get_61310000010_score() {
            assertThat(getScoreWithJokers("KTJJT")).isEqualTo(61310010110L);
        }

        @Test
        void when_cards_contain_JJKKK_should_get_70101131313_score() {
            assertThat(getScoreWithJokers("JJKKK")).isEqualTo(70101131313L);
        }

        @Test
        void when_cards_contain_JJJAT_should_get_60101011410_score() {
            assertThat(getScoreWithJokers("JJJAT")).isEqualTo(60101011410L);
        }

        @Test
        void when_cards_contain_JJJAA_should_get_60101011410_score() {
            assertThat(getScoreWithJokers("JJAAJ")).isEqualTo(70101141401L);
        }

        @Test
        void when_cards_contain_JJJAJ_should_get_70000001400_score() {
            assertThat(getScoreWithJokers("JJJAJ")).isEqualTo(70101011401L);
        }

        @Test
        void when_cards_contain_JJJJJ_should_get_70000000000_score() {
            assertThat(getScoreWithJokers("JJJJJ")).isEqualTo(70101010101L);
        }
    }
}