package com.example.reactor.flux;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class HelloWorld {

    @Test
    void hello() {
        Flux<String> stringFlow = Flux.just("one", "two", "three");
        log.info(" example for subscribe with consumer ");
        stringFlow.subscribe(System.out::println);
    }

    @Test
    void test(){
        Set<String> set = Set.of("o n e", "t w o", "t h r e e");
        Stream<Set<String>> setStream = set.stream()
                .map(s -> new HashSet<>(Arrays.asList(s.split(" "))));

        Stream<Set<String>> setStream1 = setStream.map(strings -> {
            System.out.println(strings.toArray().toString());
            return strings.stream().collect(Collectors.toSet());
        });

        System.out.println("////");
        setStream1.forEach(System.out::println);
    }

    @Test
    void name() {
        Map m = new HashMap<>();

        m.put(1, 2);

        m.entrySet().forEach(System.out::println);
    }
}
