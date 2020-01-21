package com.example.reactor.base.array;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

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
        //2
        log.info("length is " + s.length);
    }

    @Test
    void testStringToArray() {
        String s = "aaa:bbb:ccc";

        String[] split = s.split(":");

        log.info("length is {}, split[0] is {}", split.length, split[0]);
    }
}
