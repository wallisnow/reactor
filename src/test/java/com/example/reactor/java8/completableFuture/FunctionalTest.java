package com.example.reactor.java8.completableFuture;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

public class FunctionalTest {
    @Test
    void simpleTest() {
        Integer integer1 = doTransfer("jack", getJackNumber());
        Assertions.assertEquals(1, integer1);

        Integer integer2 = doTransfer("lucy", getJackNumber());
        Assertions.assertEquals(2, integer2);
    }

    private Function<String, Integer> getJackNumber() {
        return (input) -> {
            if (input.equals("jack")) {
                return 1;
            } else {
                return 2;
            }
        };
    }

    Integer doTransfer(String name, Function<String, Integer> function){
        return function.apply(name);
    }
}
