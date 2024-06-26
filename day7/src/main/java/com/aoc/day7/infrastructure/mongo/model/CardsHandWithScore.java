package com.aoc.day7.infrastructure.mongo.model;

import org.bson.types.ObjectId;

public class CardsHandWithScore {
    private ObjectId id;
    private long order;
    private String cards;
    private long bid;
    private long score;

    public CardsHandWithScore(long order, String cards, long bid, long score) {
        this.order = order;
        this.cards = cards;
        this.bid = bid;
        this.score = score;
    }

    public CardsHandWithScore() {
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

    public String getCards() {
        return cards;
    }

    public long getBid() {
        return bid;
    }

    public long getScore() {
        return score;
    }

    public void setOrder(long order) {
        this.order = order;
    }

    public void setCards(String cards) {
        this.cards = cards;
    }

    public void setBid(long bid) {
        this.bid = bid;
    }

    public void setScore(long score) {
        this.score = score;
    }
}