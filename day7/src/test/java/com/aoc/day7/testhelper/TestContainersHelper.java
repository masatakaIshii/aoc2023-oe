package com.aoc.day7.testhelper;

import com.aoc.day7.testcontainers.SchemaRegistryContainer;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
public class TestContainersHelper {
    private static final Network KAFKA_NETWORK = Network.newNetwork();
    @Container
    static KafkaContainer KAFKA = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:latest"))
            .withNetwork(KAFKA_NETWORK)
            .withEnv("KAFKA_TRANSACTION_STATE_LOG_MIN_ISR", "1")
            .withEnv("KAFKA_TRANSACTION_STATE_LOG_REPLICATION_FACTOR", "1");
    @Container
    static SchemaRegistryContainer SCHEMA_REGISTRY = new SchemaRegistryContainer("latest")
            .withKafka(KAFKA);

    @Container
    static MongoDBContainer MONGO_DB = new MongoDBContainer("mongo:5.0.26");

    @DynamicPropertySource
    static void initKafkaProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.kafka.bootstrap-servers", KAFKA::getBootstrapServers);
        String schemaRegistryUrl = STR."http://\{SCHEMA_REGISTRY.getHost()}:\{SCHEMA_REGISTRY.getFirstMappedPort()}";
        registry.add("spring.kafka.producer.properties.schema.registry.url", () -> schemaRegistryUrl);
        registry.add("spring.kafka.consumer.properties.schema.registry.url", () -> schemaRegistryUrl);
        registry.add("mongodb.uri", () -> MONGO_DB.getReplicaSetUrl());
    }
}