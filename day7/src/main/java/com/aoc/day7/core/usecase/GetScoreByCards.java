package com.aoc.day7.core.usecase;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.stream.Collectors;

public class GetScoreByCards {
    public static long getScore(String cards) {
        String[] splitStrCards = cards.split("");
        return Long.parseLong(STR."\{getCardsType(splitStrCards)}\{getScoreOfCards(splitStrCards)}");
    }

    private static Long getCardsType(String[] splitStrCards) {
        var groupingCards = Arrays.stream(splitStrCards).collect(Collectors.groupingBy(str -> str, Collectors.counting()));
        long max = groupingCards.values().stream().mapToLong(Long::longValue).max().orElseThrow();
        if (max == 3 && groupingCards.values().size() > 2) return 4L;
        if (max == 2 && groupingCards.values().size() == 3) return 3L;
        if (max <= 2) return max;
        return max + 2;
    }

    private static String getScoreOfCards(String[] splitStrCards) {
        DecimalFormat decimalFormat = new DecimalFormat("#00");
        return Arrays.stream(splitStrCards).mapToLong(oneChar -> switch (oneChar) {
            case "A" -> 14L;
            case "K" -> 13L;
            case "Q" -> 12L;
            case "J" -> 11L;
            case "T" -> 10L;
            default -> Long.parseLong(oneChar);
        }).mapToObj(decimalFormat::format).collect(Collectors.joining());
    }
}