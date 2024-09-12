package com.javastudio.mongo;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

public class IntStreamTest {
    @Test
    void name() {
        IntStream.range(1, 0).forEach(System.out::println);
    }
}
