package com.aoc.day7.infrastructure.controller;

import com.aoc.day7.core.CardsHand;
import com.aoc.day7.infrastructure.kafka.producer.CamelCardsProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@RestController
public class CamelCardsController {
    @Value("${camel.cards.filepath}")
    String filePath;
    private final CamelCardsProducer camelCardsProducer;

    public CamelCardsController(CamelCardsProducer camelCardsProducer) {
        this.camelCardsProducer = camelCardsProducer;
    }

    @PostMapping("/start")
    public void start() {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine();
            int order = 1;
            while (line != null) {
                line = br.readLine();
                String[] split = line.split(" ");
                CardsHand cardsHand = new CardsHand(order, split[0], Long.parseLong(split[1]));
                camelCardsProducer.send(cardsHand);
                order++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
