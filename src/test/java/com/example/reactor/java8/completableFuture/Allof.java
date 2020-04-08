package com.example.reactor.java8.completableFuture;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Allof {

    @Test
    void testAllOf() {
        CompletableFuture.allOf(
                CompletableFuture.runAsync(() -> {
                    log.info(Thread.currentThread().getName() + " first future");
                }),
                CompletableFuture.runAsync(() -> {
                    log.info(Thread.currentThread().getName() + " second future");
                })
        ).thenApply(__ -> {
            log.info(Thread.currentThread().getName() + " done");
            return null;
        });
    }

    @Test
    void testAllOfToArray() {
        CompletableFuture.allOf(
                List.of("first", "second")
                        .stream()
                        .map(s -> {
                            log.info("{} handling {}", Thread.currentThread().getName(), s);
                            String  i = null;
                            //will throw exception
                            //i.toUpperCase();
                            return CompletableFuture.runAsync(()->{
                                //won't throw exception
                                i.toUpperCase();
                                log.info(" {} completable future handling {}", Thread.currentThread().getName(), s);
                            });
                        })
                        .toArray(CompletableFuture[]::new)
        ).thenApply(__ -> {
            log.info(Thread.currentThread().getName() + " done");
            return null;
        });
    }
}
