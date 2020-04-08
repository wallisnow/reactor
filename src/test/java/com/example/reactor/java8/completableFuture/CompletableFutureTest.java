package com.example.reactor.java8.completableFuture;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class CompletableFutureTest {

    private static Random RAMDOM = new Random(System.currentTimeMillis());

    @Test
    void crateCompletableFutureBlocking() throws ExecutionException, InterruptedException {

        CompletableFuture<Double> completableFuture = new CompletableFuture<>();

        new Thread(() -> {
            double value = 0;
            try {
                value = getNextValue();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            completableFuture.complete(value);
        }).start();

        System.out.println("===========no==block");

        //阻塞结果
        Optional.ofNullable(completableFuture.get())
                .ifPresent(System.out::println);
    }

    @Test
    void crateCompletableFutureNoNBlocking() {

        CompletableFuture<Double> completableFuture = new CompletableFuture<>();

        new Thread(() -> {
            double value = 0;
            try {
                value = getNextValue();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            completableFuture.complete(value);
        }).start();

        System.out.println("===========no==block");

        //TimeUnit.SECONDS.sleep(3);

        //不要阻塞结果
        completableFuture.whenComplete((aDouble, throwable) -> {
            Optional.ofNullable(aDouble).ifPresent(System.out::println);
            Optional.ofNullable(throwable).ifPresent(System.out::println);
        });
    }

    @Test
    void testThenApplyAsync() throws InterruptedException {

        CompletableFuture.completedFuture("Test")
                .thenApplyAsync(s -> {
                    new Thread(() -> {
                        System.out.println(Thread.currentThread().getName() + ":" + s);
                    }).start();
                    return null;
                }).exceptionally(throwable -> {
            log.error("error ", throwable);
            return null;
        });

        //TimeUnit.SECONDS.sleep(1);
    }

    @RepeatedTest(20)
    void asyncFutureCalculate() throws ExecutionException, InterruptedException {
        Future<String> completableFuture = calculateAsync();
        String result = completableFuture.get();
        assertEquals("Hello", result);
    }

    @RepeatedTest(20)
    void asyncCalculate() throws ExecutionException, InterruptedException {
        Future<String> completableFuture = CompletableFuture.completedFuture("Hello");
        String result = completableFuture.get();
        assertEquals("Hello", result);
    }

    public Future<String> calculateAsync() {
        CompletableFuture<String> completableFuture
                = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(() -> {
            Thread.sleep(500);
            completableFuture.complete("Hello");
            return null;
        });

        return completableFuture;
    }

    private double getNextValue() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        return RAMDOM.nextDouble();
    }
}