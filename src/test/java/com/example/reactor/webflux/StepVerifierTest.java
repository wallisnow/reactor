package com.example.reactor.webflux;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;

public class StepVerifierTest {

    Flux<String> source;

    @BeforeEach
    void setUp() {
        source = Flux.just("John", "Monica", "Mark", "Cloe", "Frank", "Casper", "Olivia", "Emily", "Cate")
                .filter(name -> name.length() == 4)
                .map(String::toUpperCase);
    }

    @Test
    void testWhenSomeoneSubscribe() {
        StepVerifier
                .create(source)
                .expectNext("JOHN")
                .expectNextMatches(name -> name.startsWith("MA"))
                .expectNext("CLOE","CATE")
                .expectComplete()
                //trigger test
                .verify();
    }

    @Test
    void testMonoTerminateWithError() {
        Flux<String> error = source.concatWith(Mono.error(new IllegalArgumentException("error message")));

        StepVerifier.create(error)
                .expectNextCount(4)
                .expectErrorMatches(throwable -> throwable instanceof IllegalArgumentException && throwable.getMessage().equals("error message"))
                .verify();
    }

    @Test
    void testTimeBasedPublisher() {
        StepVerifier.withVirtualTime(()->Flux.interval(Duration.ofSeconds(1)).take(2))
                .expectSubscription()
                .expectNoEvent(Duration.ofSeconds(1))
                .expectNext(0L)
                .thenAwait(Duration.ofSeconds(1))
                .expectNext(1L)
                .verifyComplete();
    }
}
