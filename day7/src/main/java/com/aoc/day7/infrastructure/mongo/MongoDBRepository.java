package com.aoc.day7.infrastructure.mongo;

import com.aoc.day7.core.adapter.CardsHandRepository;
import com.aoc.day7.core.adapter.CountRepository;
import com.aoc.day7.core.model.CardsHandWithScore;
import com.aoc.day7.infrastructure.mongo.model.MongoDBCardsHandWithScore;
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
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Objects;

import static com.aoc.day7.infrastructure.mongo.MongoConstants.CARDS_HAND_COLLECTION;
import static com.aoc.day7.infrastructure.mongo.MongoConstants.DB_NAME;

@Repository
public class MongoDBRepository implements CardsHandRepository, CountRepository, SetRankInCardsHandRepository, GetTotalWinningRepository {
    private final MongoCollection<MongoDBCardsHandWithScore> mongoCollection;
    private final MongoCollection<MongoDBTotal> totalMongoCollection;

    public MongoDBRepository(MongoClient mongoClient) {
        MongoDatabase camelCards = mongoClient.getDatabase(DB_NAME);
        this.mongoCollection = camelCards.getCollection(CARDS_HAND_COLLECTION, MongoDBCardsHandWithScore.class);
        this.totalMongoCollection = camelCards.getCollection(CARDS_HAND_COLLECTION, MongoDBTotal.class);
    }

    @Override
    public void insertOne(CardsHandWithScore cardsHandWithScore) {
        MongoDBCardsHandWithScore mongoDBCardsHandWithScore = new MongoDBCardsHandWithScore(cardsHandWithScore.order(), cardsHandWithScore.cards(), cardsHandWithScore.bid(), cardsHandWithScore.scores());

        mongoCollection.insertOne(mongoDBCardsHandWithScore);
    }

    @Override
    public long count() {
        return mongoCollection.countDocuments();
    }

    @Override
    public void setRankInCardsHands(int skip, int limit) {
        MongoCursor<MongoDBCardsHandWithScore> cardsHandIterator = mongoCollection.find()
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
        AggregateIterable<MongoDBTotal> aggregate = totalMongoCollection.aggregate(Arrays.asList(new Document("$project",
                        new Document("result",
                                new Document("$multiply", Arrays.asList("$rank", "$bid")))),
                new Document("$group",
                        new Document("_id",
                                new BsonNull())
                                .append("total",
                                        new Document("$sum", "$result")))));
        return Objects.requireNonNull(aggregate.first()).total;
    }

    public static class MongoDBTotal {
        private ObjectId id;
        private long total;

        public MongoDBTotal() {
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
}