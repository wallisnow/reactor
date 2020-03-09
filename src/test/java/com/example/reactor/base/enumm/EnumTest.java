package com.example.reactor.base.enumm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Stream;

@Slf4j
public class EnumTest {

    @AllArgsConstructor
    enum Day {
        MON("1"),
        TUES("2"),
        WED("3"),
        THU("4"),
        FRI("5"),
        SAT("6"),
        SUN("7");

        @Getter
        private String number;
    }

    @Test
    void testValues() {
        String[] objects = Stream.of(Day.values()).map(Day::getNumber).toArray(String[]::new);
        log.info(Arrays.toString(objects));
    }
}
