package com.aoc.day7.infrastructure.kafka.consumer;

import com.aoc.day7.infrastructure.mongo.repository.CountRepository;
import com.aoc.day7.infrastructure.logger.CustomLogger;
import com.aoc.day7.infrastructure.logger.CustomLoggerFactory;
import com.aoc.day7.infrastructure.mongo.repository.GetTotalWinningRepository;
import com.aoc.day7.infrastructure.mongo.repository.SetRankInCardsHandRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

@Service
public class UpdateCardsHandRankConsumer {
    private final CustomLogger logger;
    private final CountRepository countRepository;
    private final SetRankInCardsHandRepository setRankInCardsHandRepository;
    private final GetTotalWinningRepository getTotalWinningRepository;

    public UpdateCardsHandRankConsumer(CustomLoggerFactory customLoggerFactory,
                                       CountRepository countRepository,
                                       SetRankInCardsHandRepository setRankInCardsHandRepository,
                                       GetTotalWinningRepository getTotalWinningRepository) {
        this.logger = customLoggerFactory.createLogger(UpdateCardsHandRankConsumer.class);
        this.countRepository = countRepository;
        this.setRankInCardsHandRepository = setRankInCardsHandRepository;
        this.getTotalWinningRepository = getTotalWinningRepository;
    }

    @RetryableTopic(attempts = "4", backoff = @Backoff(delay = 500, multiplier = 2, maxDelay = 10_000))
    @KafkaListener(topics = "${topic.total-cards-hand}")
    public void consume(Integer total) {
        if (countRepository.count() < total) {
            logger.error("All cards hand are not insert in collection");
            throw new IllegalStateException();
        }

        int skip = 0;
        int limit = 100;
        while (skip < total) {
            logger.info(STR."set rank in cards hand between \{skip} and \{limit}");
            setRankInCardsHandRepository.setRankInCardsHands(skip, limit);
            skip += limit;
        }

        long totalWinnings = getTotalWinningRepository.getTotal();
        logger.info(STR."result is \{totalWinnings}");
    }
}