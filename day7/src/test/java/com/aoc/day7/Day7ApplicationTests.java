package com.aoc.day7;

import com.aoc.day7.core.CardsHand;
import com.aoc.day7.infrastructure.kafka.producer.CamelCardsProducer;
import com.aoc.day7.infrastructure.logger.CustomLogger;
import com.aoc.day7.infrastructure.logger.CustomLoggerFactory;
import com.aoc.day7.testcontainers.SchemaRegistryContainer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.testcontainers.shaded.org.awaitility.Awaitility.await;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class Day7ApplicationTests {
    private static final CustomLoggerFactory customLoggerFactory = Mockito.mock(CustomLoggerFactory.class);
    private static final CustomLogger customLogger = Mockito.mock(CustomLogger.class);
    private static final Network KAFKA_NETWORK = Network.newNetwork();

    @TestConfiguration
    static class CustomLoggerFactoryContext {
        @Bean
        @Primary
        CustomLoggerFactory customLoggerFactory() {
            return customLoggerFactory;
        }
    }


    @Container
    static KafkaContainer KAFKA = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:latest"))
            .withNetwork(KAFKA_NETWORK)
            .withEnv("KAFKA_TRANSACTION_STATE_LOG_MIN_ISR", "1")
            .withEnv("KAFKA_TRANSACTION_STATE_LOG_REPLICATION_FACTOR", "1");
    @Container
    static SchemaRegistryContainer SCHEMA_REGISTRY = new SchemaRegistryContainer("latest")
            .withKafka(KAFKA);

    @DynamicPropertySource
    static void initKafkaProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.kafka.bootstrap-servers", KAFKA::getBootstrapServers);
        String schemaRegistryUrl = STR."http://\{SCHEMA_REGISTRY.getHost()}:\{SCHEMA_REGISTRY.getFirstMappedPort()}";
        registry.add("spring.kafka.producer.properties.schema.registry.url", () -> schemaRegistryUrl);
        registry.add("spring.kafka.consumer.properties.schema.registry.url", () -> schemaRegistryUrl);
    }

    @Autowired
    CamelCardsProducer camelCardsProducer;

    @Captor
    ArgumentCaptor<String> messageCaptor;

    @BeforeAll
    static void setup() {
        Mockito.when(customLoggerFactory.createLogger(any())).thenReturn(customLogger);
    }


    // TODO 1 : save in mongo collection the cards hand
    // TODO 2 : write appropriate acceptance test
    @Test
    void testSendEventsToTopic() {
        // GIVEN
        CardsHand cardsHand = new CardsHand(1, "1236zef", 120);

        // WHEN
        camelCardsProducer.send(cardsHand);
        await().pollInterval(Duration.ofSeconds(3)).atMost(10, TimeUnit.SECONDS).untilAsserted(() -> {
            Mockito.verify(customLogger, Mockito.times(3)).info(messageCaptor.capture());
        });

        // THEN
        List<String> allValues = messageCaptor.getAllValues();
        String expect = STR."cards hand data send :{\"order\": \{cardsHand.getOrder()}, \"cards\": \"\{cardsHand.getCards()}\", \"bid\": \{cardsHand.getBid()}}";
        assertThat(allValues).anyMatch(value -> value.equals(expect));
    }

}
