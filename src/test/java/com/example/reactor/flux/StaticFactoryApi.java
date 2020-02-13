package com.example.reactor.flux;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;
import java.sql.Time;
import java.time.Duration;
import java.util.SortedMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Slf4j
public class StaticFactoryApi {

    @Test
    void fromArrayTest() {
        String[] arr = new String[]{"one", "two", "three"};
        Flux<String> stringFlow = Flux.fromArray(arr);
        stringFlow.subscribe(System.out::println);
    }

    @Test
    void fromIterableTest() {
        SortedMap<String, Charset> charsetSortedMap = Charset.availableCharsets();
        Iterable<String> iterable = charsetSortedMap.keySet();
        Flux<String> charsetFlow = Flux.fromIterable(iterable);
        charsetFlow.subscribe(System.out::println);
    }

    @Test
    void fromSteamTest() {
        SortedMap<String, Charset> charsetSortedMap = Charset.availableCharsets();
        Flux<String> charsetFlow = Flux.fromStream(charsetSortedMap.keySet().stream());
        charsetFlow.subscribe(System.out::println);
    }

    @Test
    void rangeTest() {
        Flux.range(1, 5).subscribe(System.out::println);
        //exception
        //Flux.range(Integer.MAX_VALUE, 5).subscribe(System.out::println);
    }

    @Test
    void testFrom() {
        Publisher<Integer> fluxPublisher = Flux.just(1, 2, 3);
        Publisher<Integer> monoPublisher = Mono.just(0);

        System.out.println("Flux from flux");
        Flux.from(fluxPublisher).subscribe(System.out::println);

        System.out.println("Flux from mono");
        Flux.from(monoPublisher).subscribe(System.out::println);
    }

    @Test
    void testDefer() {
        AtomicInteger subscribeTime = new AtomicInteger(1);
        //实现这一的效果，返回的数据流为1~5乘以当前subscribe的次数
        Supplier<? extends Publisher<Integer>> supplier = () -> {
            Integer[] array = {1, 2, 3, 4, 5};
            int currentTime = subscribeTime.getAndIncrement();
            for (int i = 0; i < array.length; i++) {
                array[i] *= currentTime;
            }
            return Flux.fromArray(array);
        };

        Flux<Integer> deferedFlux = Flux.defer(supplier);

        subscribe(deferedFlux, subscribeTime);
        subscribe(deferedFlux, subscribeTime);
        subscribe(deferedFlux, subscribeTime);
    }

    private static void subscribe(Flux<Integer> deferedFlux, AtomicInteger subscribeTime) {
        System.out.println("Subscribe time is " + subscribeTime.get());
        deferedFlux.subscribe(System.out::println);
    }

    @Test
    void testDeferWithThread() throws InterruptedException {

        Runnable r = new Runnable() {
            int a = 5;

            @Override
            public void run() {
                Mono<Integer> monoJust = Mono.just(a);
                Mono<Integer> monoDefer = Mono.defer(() -> Mono.just(a));

                monoJust.subscribe(System.out::println);
                monoDefer.subscribe(System.out::println);

                a = 7;
                monoJust.subscribe(System.out::println);
                monoDefer.subscribe(System.out::println);
            }
        };

        r.run();

        TimeUnit.SECONDS.sleep(1);
    }

    @Test
    void testJust() throws InterruptedException {
        long current = System.currentTimeMillis();

        Mono<Long> clock = Mono.just(current);
        //time == t0
        Thread.sleep(10_000);
        //time == t10
        Long time1 = clock.block();//we use block for demonstration purposes, returns t0

        Thread.sleep(7_000);
        //time == t17
        Long time2 = clock.block();//we re-subscribe to clock, still returns t0

        //因为 Mono.just(current) 当just时已经被执行了
        Assertions.assertEquals(time1, current);
        Assertions.assertEquals(time2, current);
    }

    @Test
    void testDeferLazy() throws InterruptedException {
        Mono<Long> clock = Mono.defer(() -> Mono.just(System.currentTimeMillis()));
        //time == t0
        Thread.sleep(10_000);
        //time == t10
        clock.subscribe(System.out::println); //invoked currentTimeMillis() here and returns t10

        Thread.sleep(7_000);
        //time == t17
        clock.subscribe(System.out::println);
    }

    @Test
    void testInterval() throws InterruptedException {
        Flux.interval(Duration.ofSeconds(1))
                .take(3)
                .subscribe(aLong -> log.info(String.valueOf(aLong)));
        TimeUnit.SECONDS.sleep(5);
    }
}
