package com.example.reactor.base.string;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringTest {
    @Test
    void split() {
        String s = "aaa.bbb.ccc";
        String[] split = s.split("\\.", 3);
        Assertions.assertEquals("bbb", split[1]);


        String s2 = "aaa.bbb.ccc";
        String[] split2 = s2.split("\\.", -1);
        Assertions.assertEquals("aaa", split2[0]);
    }
}
