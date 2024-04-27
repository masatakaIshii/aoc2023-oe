package com.aoc.day7.infrastructure.controller;

import com.aoc.day7.core.model.CardsHand;
import com.aoc.day7.infrastructure.kafka.producer.CardsHandProducer;
import com.aoc.day7.infrastructure.logger.CustomLogger;
import com.aoc.day7.infrastructure.logger.CustomLoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@RestController
public class CamelCardsController {
    private final CustomLogger log;
    @Value("${camel.cards.filepath}")
    String filePath;
    private final CardsHandProducer cardsHandProducer;

    public CamelCardsController(CardsHandProducer cardsHandProducer, CustomLoggerFactory customLoggerFactory) {
        this.cardsHandProducer = cardsHandProducer;
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
                CardsHand cardsHand = new CardsHand(order++, split[0], Long.parseLong(split[1]));
                cardsHandProducer.send(cardsHand);
                line = br.readLine();
            }
            log.info(STR."Number order :\{order - 1}");
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
