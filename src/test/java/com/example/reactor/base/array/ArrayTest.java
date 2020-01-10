package com.example.reactor.base.array;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

/**
 * @author efggjjp
 * @title
 * @description
 * @createDate 12/31/19
 */
@Slf4j
public class ArrayTest {

    @Test
    void testArrayLength() {
        String[] s = new String[]{"1", "2"};
        log.info("length is " + s.length);
    }

}
