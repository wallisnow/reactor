package com.example.reactor.webflux;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.IntStream;

public class Map {

    @Test
    void name() {
        //Flatmap : 入参 mono
        Flux.just("1","2","3","oo")
                .flatMap(s -> {
                    s = s+"_flux";
                    return Mono.just(s);
                })
                .map(String::toUpperCase)
                .subscribe(System.out::println);

        //Flatmap : 入参 flux
        Flux.just("1","2","3","oo")
                .flatMap(s -> {
                    s = s+"_flux";
                    return Flux.just(s);
                })
                .map(String::toUpperCase)
                .subscribe(System.out::println);
    }

    @Test
    void close() {
        IntStream.range(1,2).close();
    }
}
