package com.example.reactor.java8;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Optional;

/**
 * @author efggjjp
 * @title
 * @description
 * @createDate 12/30/19
 */
@Slf4j
public class OptionalTest {
    @Test
    void testMap() {
        Optional<String> test = Optional.of("{\"a\":\"1\"}");
        Map<String, Object> stringObjectMap = test.map(this::parseJsonToMap).get();

        //return a optional<Map>
        test.map(this::parseJsonToMap).get();

        //error
        //test.map(this::parseJsonToMap)
                //return optional
                //.map(System.out::println);
    }

    private Map<String, Object> parseJsonToMap(String body) {
        ObjectReader objectReader = new ObjectMapper().reader().forType(Map.class);
        try {
            return objectReader.<Map<String, Object>>readValue(body);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testOptionalNullAndEmpty() {
        Optional<Object> empty = Optional.empty();
        Optional<Object> o = Optional.ofNullable(null);

        //PASS
        Assertions.assertEquals(empty,o);
    }

    @Test
    void name() {
        String aaas = OptionalClazz.pluralize("aaas");
    }


}
