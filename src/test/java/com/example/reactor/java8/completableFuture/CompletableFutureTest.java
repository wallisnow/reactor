package com.example.reactor.java8.completableFuture;

import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

import static org.junit.jupiter.api.Assertions.*;
class CompletableFutureTest {

    private static Random RAMDOM = new Random(System.currentTimeMillis());

    @Test
    void crateCompletableFutureBlocking() throws ExecutionException, InterruptedException {

        CompletableFuture<Double> completableFuture = new CompletableFuture<>();

        new Thread(()->{
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
    void crateCompletableFutureNoNBlocking()  {

        CompletableFuture<Double> completableFuture = new CompletableFuture<>();

        new Thread(()->{
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

    private double getNextValue() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        return RAMDOM.nextDouble();
    }
}