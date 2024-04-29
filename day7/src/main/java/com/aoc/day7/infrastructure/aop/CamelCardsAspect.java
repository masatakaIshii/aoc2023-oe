package com.aoc.day7.infrastructure.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CamelCardsAspect {
    private final Logger log = LoggerFactory.getLogger(CamelCardsAspect.class);
    private long startTime;
    @Pointcut("execution(public * com.aoc.day7.infrastructure.controller.CamelCardsController.start*(..))")
    private void publicControllerStartWithJokers() {
    }

    @Pointcut("execution(public * com.aoc.day7.infrastructure.kafka.consumer.UpdateCardsHandRankConsumer.consume(..))")
    private void publicUpdateCardsHandRankConsumer() {
    }

    @Before(value = "publicControllerStartWithJokers()")
    public void logBefore() {
        startTime = System.currentTimeMillis();
        log.info(STR."start time \{startTime}");
    }

    @AfterReturning(value = "publicUpdateCardsHandRankConsumer()")
    public void logAfter() {
        long endTime = System.currentTimeMillis();
        log.info(STR."end time : \{endTime}");
        log.info(STR."duration : \{endTime - startTime}");

    }
}
