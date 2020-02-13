package com.example.reactor.java8;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
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

    @Test
    void threadBehaveTest() {
        IntStream.range(1,10).forEach(i -> {
            System.out.println(Thread.currentThread().getName() + " " + i);
        });

        Map<Integer, String> integerIntegerMap = Map.of(3, "33", 6, "66", 5, "55", 10, "1010");

        //run with MAIN
        integerIntegerMap.entrySet().stream().forEach(key -> {
            System.out.println(Thread.currentThread().getName() + " " + key);
        });
        //run with MAIN
        integerIntegerMap.entrySet().forEach(key -> {
            System.out.println(Thread.currentThread().getName() + " " + key);
        });

        integerIntegerMap.entrySet().stream().parallel().forEach(key -> {
            System.out.println(Thread.currentThread().getName() + " " + key);
        });
    }
}
