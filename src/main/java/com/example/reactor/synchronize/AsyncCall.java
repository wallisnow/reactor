package com.example.reactor.synchronize;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.*;

@Slf4j
public class AsyncCall {

    private Random random = new Random(System.currentTimeMillis());

    private ExecutorService tp = Executors.newSingleThreadExecutor();


    //demo1,2,4,5调用方法
    public void call(BaseDemo demo) {
        log.info("进入 Thread: {}", Thread.currentThread().getName());
        new Thread(() -> {
            log.info("开始 Thread.run(): {}", Thread.currentThread().getName());
            long res = random.nextInt(10);
            try {
                log.info("开始等待: {} 秒", res);
                TimeUnit.SECONDS.sleep(res);
                log.info("等待: {} 秒结束", res);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //开始调用回调函数
            demo.callback(res);
            log.info("结束 Thread.run(): {}", Thread.currentThread().getName());
        }).start();
    }

    //demo3调用方法
    public Future<Long> futureCall() {
        log.info("进入 Future: {}", Thread.currentThread().getName());
        return tp.submit(() -> {
            log.info("开始提交任务到线程池 .submit(): {}", Thread.currentThread().getName());
            long res = random.nextInt(10);
            try {
                log.info("开始等待: {} 秒", res);
                TimeUnit.SECONDS.sleep(res);
                log.info("等待: {} 秒结束", res);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("结束提交任务到线程池 .submit(): {}", Thread.currentThread().getName());
            return res;
        });
    }

    public void shutdown(){
        tp.shutdown();
    }
}
