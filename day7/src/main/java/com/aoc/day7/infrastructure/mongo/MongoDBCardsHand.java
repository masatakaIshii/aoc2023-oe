package com.aoc.day7.infrastructure.mongo;

import org.bson.types.ObjectId;

public class MongoDBCardsHand {
    private ObjectId id;
    private Long order;
    private String cards;
    private Long bid;

    public MongoDBCardsHand() {
    }

    public MongoDBCardsHand(long order, String cards, long bid) {
        this.order = order;
        this.cards = cards;
        this.bid = bid;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Long getOrder() {
        return order;
    }

    public void setOrder(Long order) {
        this.order = order;
    }

    public String getCards() {
        return cards;
    }

    public void setCards(String cards) {
        this.cards = cards;
    }

    public Long getBid() {
        return bid;
    }

    public void setBid(Long bid) {
        this.bid = bid;
    }

}
