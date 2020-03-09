package com.example.reactor.java8;

import java.util.Optional;

public class OptionalClazz {

    public static String pluralize(String kind) {
        return Optional.ofNullable(kind)
                .map(String::toLowerCase)
                .filter(s -> !s.endsWith("s"))
                .map(e -> e + "s")
                .orElse("");
    }
}
