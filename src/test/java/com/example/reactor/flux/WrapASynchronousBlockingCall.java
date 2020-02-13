package com.example.reactor.flux;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.UUID;
import java.util.function.Consumer;

@Slf4j
public class WrapASynchronousBlockingCall {

    @Test
    @DisplayName("http://next.projectreactor.io/docs/core/snapshot/reference/index.html#faq.wrap-blocking")
    void testWrapUUID() {
        generateId().map(
                uuid -> {
                    log.info("uuid is {}", uuid);
                    return "new " + uuid.toString();
                })
                .subscribe(log::info);
    }

    private Mono<UUID> generateId() {
        // UUID.randomUUID is a blocking operation
        return Mono.fromCallable(UUID::randomUUID)
                .subscribeOn(Schedulers.boundedElastic());
    }
}
