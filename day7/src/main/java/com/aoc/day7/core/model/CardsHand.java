package com.aoc.day7.core.model;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public record CardsHand(long order, String cards, long bid) {

    public long getScore() {
        String[] splitStrCards = cards.split("");
        return Long.parseLong(STR."\{getMaxOccurrence(splitStrCards)}\{getScoreOfCards(splitStrCards)}");
    }

    private Long getMaxOccurrence(String[] splitStrCards) {
        return Arrays.stream(splitStrCards).collect(Collectors.groupingBy(str -> str, Collectors.counting()))
                .entrySet().stream()
                .max((entry1, entry2) -> Math.toIntExact(entry1.getValue() - entry2.getValue()))
                .map(Map.Entry::getValue)
                .orElseThrow();
    }

    private String getScoreOfCards(String[] splitStrCards) {
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
