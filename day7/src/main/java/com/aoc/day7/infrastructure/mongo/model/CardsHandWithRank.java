package com.aoc.day7.infrastructure.mongo.model;

import org.bson.types.ObjectId;

public class CardsHandWithRank {
    private ObjectId id;
    private long order;
    private String cards;
    private long bid;
    private Long rank;

    public CardsHandWithRank(long order, String cards, long bid, Long rank) {
        this.order = order;
        this.cards = cards;
        this.bid = bid;
        this.rank = rank;
    }

    public CardsHandWithRank() {
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public long getOrder() {
        return order;
    }

    public void setOrder(long order) {
        this.order = order;
    }

    public String getCards() {
        return cards;
    }

    public void setCards(String cards) {
        this.cards = cards;
    }

    public long getBid() {
        return bid;
    }

    public void setBid(long bid) {
        this.bid = bid;
    }

    public Long getRank() {
        return rank;
    }

    public void setRank(Long rank) {
        this.rank = rank;
    }
}