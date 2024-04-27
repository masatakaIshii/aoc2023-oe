package com.aoc.day7.infrastructure.mongo;

import com.aoc.day7.core.model.CardsHand;
import com.aoc.day7.core.adapter.CardsHandRepository;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.springframework.stereotype.Repository;

import static com.aoc.day7.infrastructure.mongo.MongoConstants.CARDS_HAND_COLLECTION;
import static com.aoc.day7.infrastructure.mongo.MongoConstants.DB_NAME;

@Repository
public class MongoDBCardHandRepository implements CardsHandRepository {
    private final MongoCollection<MongoDBCardsHand> mongoCollection;

    public MongoDBCardHandRepository(MongoClient mongoClient) {
        MongoDatabase camelCards = mongoClient.getDatabase(DB_NAME);
        this.mongoCollection = camelCards.getCollection(CARDS_HAND_COLLECTION, MongoDBCardsHand.class);
    }

    public void insertOne(CardsHand cardsHand) {
        MongoDBCardsHand mongoDBCardsHand = new MongoDBCardsHand(cardsHand.getOrder(), cardsHand.getCards(), cardsHand.getBid());

        mongoCollection.insertOne(mongoDBCardsHand);
    }
}
