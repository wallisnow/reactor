package com.example.reactor.base.regex;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

public class RegexTest {

    @Test
    void word() {
        // Create a Pattern object
        // match only one pattern
        Pattern r = Pattern.compile("\\b(and|or|AND|OR)\\b");
        Assertions.assertTrue(r.matcher("and").matches());

        // match only one to three patterns
        Pattern r1 = Pattern.compile("\\b(and|or|AND|OR){1,3}\\b");
        Assertions.assertTrue(r1.matcher("andANDor").matches());

        // match over than zero patterns
        Pattern r2 = Pattern.compile("\\b(and|or|AND|OR){1,}\\b");
        Assertions.assertTrue(r2.matcher("andANDororOR").matches());

        Pattern r3 = Pattern.compile("\\sand\\s");
        Assertions.assertTrue(r3.matcher(" and ").matches());

        Pattern r4 = Pattern.compile("(\\sand\\s|\\sor\\s|\\sAND\\s|\\sOR\\s){1,}");
        Assertions.assertTrue(r4.matcher(" and ").matches());
        Assertions.assertTrue(r4.matcher(" and  or  AND ").matches());
    }
}
