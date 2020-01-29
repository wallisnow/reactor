package com.example.reactor.java8;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

public class Stream {

    @Test
    void finalTest() {
        IntStream.range(6, 10).map(
                i -> {
                    final int y = i + 1;
                    final int x = i + 2;
                    return y/x;
                }
        ).forEach(System.out::println);
    }
}
