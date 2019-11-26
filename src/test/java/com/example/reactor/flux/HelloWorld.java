package com.example.reactor.flux;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

@Slf4j
public class HelloWorld {

    @Test
    void hello() {
        Flux<String> stringFlow = Flux.just("one", "two", "three");
        log.info(" example for subscribe with consumer ");
        stringFlow.subscribe(System.out::println);
    }
}
