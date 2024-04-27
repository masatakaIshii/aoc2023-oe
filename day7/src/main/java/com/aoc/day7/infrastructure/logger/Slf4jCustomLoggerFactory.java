package com.aoc.day7.infrastructure.logger;

import org.springframework.stereotype.Component;

@Component
public class Slf4jCustomLoggerFactory implements CustomLoggerFactory{
    public <T> Slf4jCustomLogger createLogger(Class<T> tClass) {
        return new Slf4jCustomLogger(tClass);
    }
}
