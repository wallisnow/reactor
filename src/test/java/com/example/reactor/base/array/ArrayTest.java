package com.example.reactor.base.array;

import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

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

    @Test
    void testArraysCopy() {
        String[] arr = {"aa", "bb", "cc"};
        Assertions.assertEquals(3, arr.length);
        String[] newArr = Arrays.copyOf(arr, 5);
        Assertions.assertEquals(5, newArr.length);
        Arrays.stream(newArr).forEach(System.out::println);
    }
}
