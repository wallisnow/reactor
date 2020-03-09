package com.example.reactor.flux;

import org.junit.jupiter.api.*;
import reactor.blockhound.BlockHound;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class DetectBlockingCall {

    // 2. 定义一个阻塞方法
    void blockingCall() {
        Mono.delay(Duration.ofSeconds(1))
                .doOnNext(it -> {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                })
                .block();
    }

    @Test
    void blockHoundSimpleTest() {
        //3. 调用阻塞方法
        Throwable throwable = Assertions.assertThrows(Throwable.class, this::blockingCall);

        //4. 验证阻塞方法是否抛出异常
        Assertions.assertTrue(throwable.getMessage().contains("Blocking call!"));
    }


}
