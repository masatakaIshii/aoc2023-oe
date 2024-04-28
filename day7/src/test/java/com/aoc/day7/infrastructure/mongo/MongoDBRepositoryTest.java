package com.aoc.day7.infrastructure.mongo;

import com.aoc.day7.infrastructure.mongo.model.CardsHandWithRank;
import com.aoc.day7.infrastructure.mongo.model.CardsHandWithScore;
import com.aoc.day7.infrastructure.mongo.repository.MongoDBRepository;
import com.aoc.day7.testhelper.TestContainersHelper;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.StreamSupport;

import static com.aoc.day7.infrastructure.mongo.repository.MongoConstants.CARDS_HAND_COLLECTION;
import static com.aoc.day7.infrastructure.mongo.repository.MongoConstants.DB_NAME;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MongoDBRepositoryTest extends TestContainersHelper {
    @Autowired
    MongoDBRepository mongoDBRepository;

    @Autowired
    MongoClient mongoClient;

    MongoCollection<CardsHandWithScore> mongoDBCardsHandWithScoreCollection;
    private MongoCollection<CardsHandWithRank> mongoDBCardsHandWithRankCollection;


    @BeforeEach
    void setup() {
        mongoDBCardsHandWithScoreCollection = mongoClient
                .getDatabase(DB_NAME)
                .getCollection(CARDS_HAND_COLLECTION, CardsHandWithScore.class);
        mongoDBCardsHandWithRankCollection = mongoClient
                .getDatabase(DB_NAME)
                .getCollection(CARDS_HAND_COLLECTION, CardsHandWithRank.class);
        mongoDBCardsHandWithRankCollection.drop();
    }

    @Nested
    class SetRankinCardsHand {
        @Test
        void should_set_rank_by_score() {
            mongoDBCardsHandWithScoreCollection.insertMany(List.of(
                    new CardsHandWithScore(1L, "32T3K", 765L, 20302100313L),
                    new CardsHandWithScore(2L, "T55J5", 684L, 31005051105L),
                    new CardsHandWithScore(3L, "KK677", 28L, 21313060707L)
            ));

            mongoDBRepository.setRankInCardsHands(0, 3);

            Spliterator<CardsHandWithRank> spliterator = mongoDBCardsHandWithRankCollection.find().spliterator();
            List<CardsHandWithRank> result = StreamSupport.stream(
                    spliterator,
                    false
            ).toList();

            assertThat(result).hasSize(3);
            List<Long> listRanks = result.stream().map(CardsHandWithRank::getRank).toList();
            assertThat(listRanks).isEqualTo(List.of(1L, 3L, 2L));
        }

        @Test
        void should_set_rank_by_score_only_between_skip_and_limit_sorted_cards_hands() {
            mongoDBCardsHandWithScoreCollection.insertMany(List.of(
                    new CardsHandWithScore(1L, "32T3K", 765L, 20302100313L),
                    new CardsHandWithScore(2L, "T55J5", 684L, 31005051105L),
                    new CardsHandWithScore(3L, "KK677", 28L, 21313060707L)
            ));

            mongoDBRepository.setRankInCardsHands(0, 2);

            Spliterator<CardsHandWithRank> spliterator = mongoDBCardsHandWithRankCollection.find().spliterator();
            List<CardsHandWithRank> result = StreamSupport.stream(
                    spliterator,
                    false
            ).toList();

            assertThat(result).hasSize(3);
            List<Long> listRanks = result.stream().map(CardsHandWithRank::getRank).toList();
            ArrayList<Long> expected = new ArrayList<>() {
                {
                    add(1L);
                    add(null);
                    add(2L);
                }
            };
            assertThat(listRanks).isEqualTo(expected);
        }

        @Test
        void when_2_cards_hands_have_same_score_should_set_rank_by_order() {
            mongoDBCardsHandWithScoreCollection.insertMany(List.of(
                    new CardsHandWithScore(2L, "32T3K", 765L, 20302100313L),
                    new CardsHandWithScore(1L, "32T3K", 684L, 20302100313L),
                    new CardsHandWithScore(3L, "KK677", 28L, 21313060707L)
            ));

            mongoDBRepository.setRankInCardsHands(0, 3);

            Spliterator<CardsHandWithRank> spliterator = mongoDBCardsHandWithRankCollection.find().spliterator();
            List<CardsHandWithRank> result = StreamSupport.stream(
                    spliterator,
                    false
            ).toList();

            assertThat(result).hasSize(3);
            List<Long> listRanks = result.stream().map(CardsHandWithRank::getRank).toList();
            assertThat(listRanks).isEqualTo(List.of(2L, 1L, 3L));
        }
    }
}