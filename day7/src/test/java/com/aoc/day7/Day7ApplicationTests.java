package com.aoc.day7;

import com.aoc.day7.infrastructure.controller.CamelCardsController;
import com.aoc.day7.infrastructure.logger.CustomLogger;
import com.aoc.day7.infrastructure.logger.CustomLoggerFactory;
import com.aoc.day7.infrastructure.mongo.model.CardsHandWithRank;
import com.aoc.day7.testhelper.TestContainersHelper;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.aoc.day7.infrastructure.mongo.repository.MongoConstants.CARDS_HAND_COLLECTION;
import static com.aoc.day7.infrastructure.mongo.repository.MongoConstants.DB_NAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.testcontainers.shaded.org.awaitility.Awaitility.await;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Day7ApplicationTests extends TestContainersHelper {
    private static final CustomLoggerFactory customLoggerFactory = Mockito.mock(CustomLoggerFactory.class);
    private static final CustomLogger customLogger = Mockito.mock(CustomLogger.class);

    @TestConfiguration
    static class CustomLoggerFactoryContext {
        @Bean
        @Primary
        CustomLoggerFactory customLoggerFactory() {
            return customLoggerFactory;
        }
    }

    @Captor
    ArgumentCaptor<String> messageCaptor;

    @Autowired
    MongoClient mongoClient;

    @Autowired
    CamelCardsController camelCardsController;

    @BeforeAll
    static void setup() {
        Mockito.when(customLoggerFactory.createLogger(any())).thenReturn(customLogger);
    }

    @BeforeEach
    void beforeEach() {
        MongoCollection<CardsHandWithRank> mongoDbCardsHandWithRankCollection = getMongoDbCardsHandWithRankCollection();
        mongoDbCardsHandWithRankCollection.drop();
    }

    @Test
    void acceptance_test() {
        // GIVEN
        // WHEN
        camelCardsController.start();
        await().pollInterval(Duration.ofSeconds(4)).atMost(10, TimeUnit.SECONDS).untilAsserted(() -> {
            Mockito.verify(customLogger, Mockito.atLeastOnce()).info(messageCaptor.capture());

            // THEN
            List<String> allValues = messageCaptor.getAllValues();
            String expect = "result is 6440";
            assertThat(allValues).anyMatch(value -> value.equals(expect));
        });
    }

    @Test
    void acceptance_test2() {
        // GIVEN
        // WHEN
        camelCardsController.startWithJokers();
        await().pollInterval(Duration.ofSeconds(4)).atMost(10, TimeUnit.SECONDS).untilAsserted(() -> {
            Mockito.verify(customLogger, Mockito.atLeastOnce()).info(messageCaptor.capture());

            // THEN
            List<String> allValues = messageCaptor.getAllValues();
            String expect = "result is 5905";
            assertThat(allValues).anyMatch(value -> value.equals(expect));
        });
    }

    @NotNull
    private MongoCollection<CardsHandWithRank> getMongoDbCardsHandWithRankCollection() {
        MongoDatabase camelCards = mongoClient.getDatabase(DB_NAME);
        return camelCards.getCollection(CARDS_HAND_COLLECTION, CardsHandWithRank.class);
    }
}