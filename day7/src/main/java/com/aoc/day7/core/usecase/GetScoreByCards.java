package com.aoc.day7.core.usecase;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GetScoreByCards {
    public static long getScore(String cards) {
        String[] splitStrCards = cards.split("");
        Long cardsType = getCardsType(splitStrCards, GetScoreByCards::getMaxWithCards);
        String score = getScoreOfCards(splitStrCards, GetScoreByCards::mapOneCardToScorePart);
        return Long.parseLong(STR."\{cardsType}\{score}");
    }

    public static long getScoreWithJokers(String cards) {
        String[] splitStrCards = cards.split("");
        Long cardsType = getCardsType(splitStrCards, GetScoreByCards::getMaxWithCardsAndJokers);
        String score = getScoreOfCards(splitStrCards, GetScoreByCards::mapOneCardToScorePartWithJoker);
        return Long.parseLong(STR."\{cardsType}\{score}");
    }

    private static long getMaxWithCards(Map<String, Long> groupingCards) {
        return groupingCards.values().stream().mapToLong(Long::longValue).max().orElseThrow();
    }

    private static long getMaxWithCardsAndJokers(Map<String, Long> groupingCards) {
        Long numberJoker = groupingCards.getOrDefault("J", 0L);
        return groupingCards.values().stream().mapToLong(Long::longValue).max().orElseThrow() + numberJoker;
    }

    private static Long getCardsType(String[] splitStrCards, Function<Map<String, Long>, Long> getMax) {
        var groupingCards = Arrays.stream(splitStrCards).collect(Collectors.groupingBy(str -> str, Collectors.counting()));
        long max = getMax.apply(groupingCards);
        if (max == 3 && groupingCards.values().size() > 2) return 4L;
        if (max == 2 && groupingCards.values().size() == 3) return 3L;
        if (max <= 2) return max;
        return max + 2;
    }

    private static String getScoreOfCards(String[] splitStrCards, Function<String, Long> mapOneCardToOneScorePart) {
        DecimalFormat decimalFormat = new DecimalFormat("#00");
        return Arrays.stream(splitStrCards)
                .mapToLong(mapOneCardToOneScorePart::apply)
                .mapToObj(decimalFormat::format)
                .collect(Collectors.joining());
    }

    private static long mapOneCardToScorePart(String oneChar) {
        return switch (oneChar) {
            case "A" -> 14L;
            case "K" -> 13L;
            case "Q" -> 12L;
            case "J" -> 11L;
            case "T" -> 10L;
            default -> Long.parseLong(oneChar);
        };
    }

    private static long mapOneCardToScorePartWithJoker(String oneChar) {
        return switch (oneChar) {
            case "A" -> 14L;
            case "K" -> 13L;
            case "Q" -> 12L;
            case "J" -> 0L;
            case "T" -> 10L;
            default -> Long.parseLong(oneChar);
        };
    }
}