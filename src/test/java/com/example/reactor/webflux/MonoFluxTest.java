package com.example.reactor.webflux;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

public class MonoFluxTest {

    @Test
    void zipWith() {

        Flux.just("a", "b", "c")
                .zipWith(Flux.just(123));

    }
}
