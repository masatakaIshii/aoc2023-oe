package com.aoc.day7.core.model;

import java.util.Objects;

public final class CardsHand {
    private final long order;
    private final String cards;
    private final long bid;

    public CardsHand(long order, String cards, long bid) {
        this.order = order;
        this.cards = cards;
        this.bid = bid;
    }


    public long getOrder() {
        return order;
    }

    public String getCards() {
        return cards;
    }

    public long getBid() {
        return bid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardsHand cardsHand = (CardsHand) o;
        return order == cardsHand.order;
    }

    @Override
    public int hashCode() {
        return Objects.hash(order);
    }

    @Override
    public String toString() {
        return STR."CardsHand[order=\{order}, cards=\{cards}, bid=\{bid}\{']'}";
    }

}
