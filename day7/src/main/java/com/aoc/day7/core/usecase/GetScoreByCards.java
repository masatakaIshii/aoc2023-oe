package com.aoc.day7.core.usecase;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GetScoreByCards {
    public static long getScore(String cards) {
        String[] splitStrCards = cards.split("");
        Long cardsType = getCardsType(splitStrCards);
        String score = getScoreOfCards(splitStrCards, GetScoreByCards::mapOneCardToScorePart);
        return Long.parseLong(STR."\{cardsType}\{score}");
    }

    public static long getScoreWithJokers(String cards) {
        String[] splitStrCards = cards.split("");
        Long cardsType = getCardsTypeWithJoker(splitStrCards);
        String tailScoreStr = getScoreOfCards(splitStrCards, GetScoreByCards::mapOneCardToScorePartWithJoker);
        return Long.parseLong(STR."\{cardsType}\{tailScoreStr}");
    }

    private static long getMaxCardsOccurrence(Map<String, Long> groupingCards) {
        return groupingCards.values().stream().mapToLong(Long::longValue).max().orElseThrow();
    }

    private static long getMaxCardsOccurrenceWithJokers(Map<String, Long> groupingCards) {
        long maxWithCardsWithoutJoker = groupingCards.entrySet().stream()
                .filter(entry -> !entry.getKey().equals("J"))
                .mapToLong(Map.Entry::getValue).max()
                .orElse(0L);
        long numberJoker = groupingCards.getOrDefault("J", 0L);

        return maxWithCardsWithoutJoker + numberJoker;
    }

    private static Long getCardsType(String[] splitStrCards) {
        var groupingCards = Arrays.stream(splitStrCards).collect(Collectors.groupingBy(str -> str, Collectors.counting()));

        return getCardsTypeValue(getMaxCardsOccurrence(groupingCards), groupingCards.size());
    }

    private static Long getCardsTypeWithJoker(String[] splitStrCards) {
        var groupingCards = Arrays.stream(splitStrCards).collect(Collectors.groupingBy(str -> str, Collectors.counting()));

        return getCardsTypeValue(
                getMaxCardsOccurrenceWithJokers(groupingCards),
                getNumberGroupingCardsWithoutJoker(groupingCards)
        );
    }

    private static long getNumberGroupingCardsWithoutJoker(Map<String, Long> groupingCards) {
        return groupingCards.keySet().stream().filter(key -> !key.equals("J")).count();
    }

    private static long getCardsTypeValue(long max, long numberGroupingCardsWithoutJoker) {
        if (max <= 2 && numberGroupingCardsWithoutJoker > 3) return max;
        if (max == 3 && numberGroupingCardsWithoutJoker > 2) return 4L;
        if (max == 2) return 3L;
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
            case "J" -> 1L;
            case "T" -> 10L;
            default -> Long.parseLong(oneChar);
        };
    }
}