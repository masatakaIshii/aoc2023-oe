package com.aoc.day7.infrastructure.mongo;

import com.aoc.day7.core.adapter.CardsHandWithScoreRepository;
import com.aoc.day7.core.model.CardsHandWithScore;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.springframework.stereotype.Repository;

import static com.aoc.day7.infrastructure.mongo.MongoConstants.CARDS_HAND_WITH_SCORE_COLLECTION;
import static com.aoc.day7.infrastructure.mongo.MongoConstants.DB_NAME;

@Repository
public class MongoDBRepository implements CardsHandWithScoreRepository {
    private final MongoCollection<MongoDBCardsHandWithScore> mongoCollection;

    public MongoDBRepository(MongoClient mongoClient) {
        MongoDatabase camelCards = mongoClient.getDatabase(DB_NAME);
        this.mongoCollection = camelCards.getCollection(CARDS_HAND_WITH_SCORE_COLLECTION, MongoDBCardsHandWithScore.class);
    }

    @Override
    public void insertOne(CardsHandWithScore cardsHandWithScore) {
        MongoDBCardsHandWithScore mongoDBCardsHandWithScore = new MongoDBCardsHandWithScore(cardsHandWithScore.order(), cardsHandWithScore.cards(), cardsHandWithScore.bid(), cardsHandWithScore.scores());

        mongoCollection.insertOne(mongoDBCardsHandWithScore);
    }
}
