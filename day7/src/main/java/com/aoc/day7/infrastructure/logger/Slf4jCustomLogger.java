package com.aoc.day7.infrastructure.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4jCustomLogger implements CustomLogger {
    private final Logger logger;
    public <T> Slf4jCustomLogger(Class<T> tClass) {
        logger = LoggerFactory.getLogger(tClass);
    }

    public void info(String message) {
        logger.info(message);
    }

    @Override
    public void error(String messageError) {
        logger.error(messageError);
    }
}
