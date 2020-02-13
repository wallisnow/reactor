package com.example.reactor.webflux;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

@Slf4j
@SpringBootTest
class BlockingThreadInNonBlockingTest {

    @Autowired
    BlockingThreadInNonBlocking blockingThreadInNonBlocking;

    @Test
    void createBlockingAllowedMono() {
        StepVerifier.create(blockingThreadInNonBlocking.createBlockingIsNotAllowedMono())
                .expectErrorMatches(e->{
                    log.info(e.getMessage());
                    return e.getMessage().contains("Blocking call!");
                }).verify();
    }

    @Test
    void blockingIsNotAllowed() {
        StepVerifier.create(blockingThreadInNonBlocking.createBlockingAllowedMono())
                .expectNext(1)
                .verifyComplete();
    }

    @Test
    void blockingCall() {
        StepVerifier.create(Mono.zip(Mono.just(UUID.randomUUID()),Mono.just(UUID.randomUUID())))
                .expectFusion()
                .expectComplete();
    }
}