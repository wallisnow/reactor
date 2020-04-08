package com.example.reactor.java8.completableFuture;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CompletableFutureDemoTest {

    CompletableFutureDemo completableFutureDemo;

    @BeforeEach
    void setUp() {
        completableFutureDemo = new CompletableFutureDemo();
    }

    @Test
    void test1() throws IOException {
        // 这里 由于main 被回收,　thread0 空闲, 它 会执行剩余程序
        completableFutureDemo.subThreadExecuteWhenCompleted();
    }

    @Test
    void test2() throws IOException, InterruptedException {
        // 这里 由于main  睡眠后空闲没有被回收,　main 会执行剩余程序
        completableFutureDemo.mainThreadExecuteWhenCompleted();
    }
}