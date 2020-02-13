package com.example.reactor.webflux;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.TimeUnit;

@Component
public class BlockingThreadInNonBlocking {

    public Mono<Integer> createBlockingAllowedMono() {
        return getBlockingMono()
                .subscribeOn(Schedulers.elastic());
    }

    public Mono<Integer> createBlockingIsNotAllowedMono() {
        return getBlockingMono()
                .subscribeOn(Schedulers.parallel());
    }

    private Mono<Integer> getBlockingMono() {
        return Mono.just(1).doOnNext(__ -> block());
    }

    private void block() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
