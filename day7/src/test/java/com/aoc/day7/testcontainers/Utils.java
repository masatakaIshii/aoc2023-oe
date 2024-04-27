package com.aoc.day7.testcontainers;

import java.io.IOException;
import java.net.ServerSocket;

final class Utils {
    public static final String CONFLUENT_PLATFORM_VERSION = "7.4.4";

    private Utils() {
    }

    /**
     * Retrieves a random port that is currently not in use on this machine.
     *
     * @return a free port
     * @throws IOException wraps the exceptions which may occur during this method call.
     */
    static int getRandomFreePort() throws IOException {
        try (var serverSocket = new ServerSocket(0)) {
            return serverSocket.getLocalPort();
        }
    }
}
