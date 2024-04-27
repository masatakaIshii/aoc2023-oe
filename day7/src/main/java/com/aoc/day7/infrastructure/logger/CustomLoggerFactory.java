package com.aoc.day7.infrastructure.logger;

public interface CustomLoggerFactory {
    <T> CustomLogger createLogger(Class<T> tClass);
}
