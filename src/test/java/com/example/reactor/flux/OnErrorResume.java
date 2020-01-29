package com.example.reactor.flux;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import java.nio.file.AccessDeniedException;

@Slf4j
public class OnErrorResume {

    @Test
    void testOutOfFlatmap() {
        Mono.just("a")
                .flatMap(s -> s.equals("b") ? Mono.just("ignored") : Mono.error(new AccessDeniedException("some errors")))
                .onErrorResume(AccessDeniedException.class, e -> {
                    log.info("error is :" + e.getMessage());
                    return Mono.just("test");
                })
                .subscribe(log::info);
    }

    @Test
    void testInsideOfFlatmap() {
        Mono.just("a")
                .flatMap(s -> (s.equals("b") ? Mono.just("ignored") : Mono.error(new AccessDeniedException("some errors")))
                        .onErrorResume(AccessDeniedException.class, e -> {
                            log.info(e.getMessage() + " are resumed");
                            return Mono.empty();
                        })
                )
                .subscribe();
    }
}
