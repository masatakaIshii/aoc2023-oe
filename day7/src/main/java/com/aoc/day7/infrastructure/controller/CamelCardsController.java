package com.aoc.day7.infrastructure.controller;

import com.aoc.day7.core.model.CardsHandWithScore;
import com.aoc.day7.infrastructure.kafka.producer.CardsHandWithScoreProducer;
import com.aoc.day7.infrastructure.kafka.producer.UpdateCardsHandRankProducer;
import com.aoc.day7.infrastructure.logger.CustomLogger;
import com.aoc.day7.infrastructure.logger.CustomLoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static com.aoc.day7.core.usecase.GetScoreByCards.getScore;

@RestController
public class CamelCardsController {
    private final CustomLogger log;
    @Value("${camel.cards.filepath}")
    String filePath;
    private final CardsHandWithScoreProducer cardsHandWithScoreProducer;
    private final UpdateCardsHandRankProducer updateCardsHandRankProducer;

    public CamelCardsController(CardsHandWithScoreProducer cardsHandWithScoreProducer, UpdateCardsHandRankProducer updateCardsHandRankProducer, CustomLoggerFactory customLoggerFactory) {
        this.cardsHandWithScoreProducer = cardsHandWithScoreProducer;
        this.updateCardsHandRankProducer = updateCardsHandRankProducer;
        log = customLoggerFactory.createLogger(CamelCardsController.class);
    }

    @PostMapping("/start")
    public void start() {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine();
            int order = 1;
            log.info("File read and card hand event start");
            while (line != null) {
                String[] split = line.split(" ");
                cardsHandWithScoreProducer.send(new CardsHandWithScore(order++, split[0], Long.parseLong(split[1]), getScore(split[0])));
                line = br.readLine();
            }
            log.info(STR."Number order :\{order - 1}");
            updateCardsHandRankProducer.send(order - 1);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}