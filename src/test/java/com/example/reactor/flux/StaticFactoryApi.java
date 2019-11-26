package com.example.reactor.flux;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.nio.charset.Charset;
import java.util.SortedMap;

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
        Flux.range(1,5).subscribe(System.out::println);
        //exception
        //Flux.range(Integer.MAX_VALUE, 5).subscribe(System.out::println);
    }


}
