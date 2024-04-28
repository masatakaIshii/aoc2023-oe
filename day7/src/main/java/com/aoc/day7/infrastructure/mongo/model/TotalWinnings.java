package com.aoc.day7.infrastructure.mongo.model;

import org.bson.types.ObjectId;

public class TotalWinnings {
    private ObjectId id;
    private long total;

    public TotalWinnings() {
        // to be parsed by mongo DB
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
