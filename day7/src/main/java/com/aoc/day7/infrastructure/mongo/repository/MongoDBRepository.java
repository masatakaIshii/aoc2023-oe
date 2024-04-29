package com.aoc.day7.infrastructure.mongo.repository;

import com.aoc.day7.core.model.CardsHand;
import com.aoc.day7.infrastructure.mongo.model.CardsHandWithScore;
import com.aoc.day7.infrastructure.mongo.model.TotalWinnings;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import org.bson.BsonNull;
import org.bson.Document;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Objects;

import static com.aoc.day7.infrastructure.mongo.repository.MongoConstants.CARDS_HAND_COLLECTION;
import static com.aoc.day7.infrastructure.mongo.repository.MongoConstants.DB_NAME;

@Repository
public class MongoDBRepository implements InsertCardsHandRepository, CountRepository, SetRankInCardsHandRepository, GetTotalWinningRepository {
    private final MongoCollection<CardsHandWithScore> mongoCollection;
    private final MongoCollection<TotalWinnings> totalMongoCollection;

    public MongoDBRepository(MongoClient mongoClient) {
        MongoDatabase camelCards = mongoClient.getDatabase(DB_NAME);
        this.mongoCollection = camelCards.getCollection(CARDS_HAND_COLLECTION, CardsHandWithScore.class);
        this.totalMongoCollection = camelCards.getCollection(CARDS_HAND_COLLECTION, TotalWinnings.class);
    }

    @Override
    public void insertOne(CardsHand cardsHand) {
        CardsHandWithScore cardsHandWithScore = new CardsHandWithScore(cardsHand.order(), cardsHand.cards(), cardsHand.bid(), cardsHand.scores());

        mongoCollection.insertOne(cardsHandWithScore);
    }

    @Override
    public long count() {
        return mongoCollection.countDocuments();
    }

    @Override
    public void setRankInCardsHands(int skip, int limit) {
        MongoCursor<CardsHandWithScore> cardsHandIterator = mongoCollection.find()
                .sort(Sorts.ascending("score", "order"))
                .skip(skip).limit(limit).iterator();
        int rank = skip + 1;

        while (cardsHandIterator.hasNext()) {
            var current = cardsHandIterator.next();
            mongoCollection.updateOne(
                    Filters.eq("_id", current.getId()),
                    Updates.set("rank", rank));

            rank++;
        }
    }

    @Override
    public long getTotal() {
        AggregateIterable<TotalWinnings> aggregate = totalMongoCollection.aggregate(Arrays.asList(
                projectResultFieldContainMultiplyRankAndBid(),
                groupSumOfResults()
        ));
        return Objects.requireNonNull(aggregate.first()).getTotal();
    }

    private static Document projectResultFieldContainMultiplyRankAndBid() {
        return new Document("$project",
                new Document("result",
                        new Document("$multiply", Arrays.asList("$rank", "$bid"))));
    }

    private static Document groupSumOfResults() {
        return new Document("$group",
                new Document("_id",
                        new BsonNull())
                        .append("total",
                                new Document("$sum", "$result")));
    }

}