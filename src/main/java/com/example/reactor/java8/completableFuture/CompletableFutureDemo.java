package com.example.reactor.java8.completableFuture;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Slf4j
@NoArgsConstructor
public class CompletableFutureDemo {

    public static void main(String[] args) throws Exception {

    }

    public void subThreadExecuteWhenCompleted() throws IOException {
        CompletableFuture<Integer> future = new CompletableFuture<Integer>();
        log.info(Thread.currentThread().getName() + "main 执行开始");

        new Thread(() -> {
            // 子线程A启动
            log.info(Thread.currentThread().getName() + "子线程A启动");
            try {
                log.info(Thread.currentThread().getName() + "子线程A沉睡2s");
                Thread.sleep(2000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info(Thread.currentThread().getName() + "子线程A令future完成");

            // 当子线程A执行到f.complete的时候，会去看是否有注册好的f的then或者when（非async的），如果有的话，会顺次去执行。
            future.complete(100);

            log.info(Thread.currentThread().getName() + "子线程A结束");
        }).start();


        // 当前线程（主线程）执行到这里的时候，如果子线程还没有执行到f.complete(100)，
        // 那么当前线程会把whenComplete事件注册起来，并且说好哪个线程执行了f.complete(100)，
        // 哪个线程就负责执行whenComplete的内容。
        // 如果当前线程（主线程）执行到这里的时候，f.complete(100)已经被其他线程执行完毕了。
        // 那么只有当前线程自己来执行whenComplete里面的内容了。

        future.whenComplete((i, ex) -> {
            // 这个场景下，whenComplete的回调的执行线程会是子线程A
            log.info(Thread.currentThread().getName() + "complete 结束后 , 开始做其他事情");
            try {
                log.info(Thread.currentThread().getName() + "沉睡10s");
                Thread.sleep(1000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info(Thread.currentThread().getName() + "其他事情完成");
        });


        log.info(Thread.currentThread().getName() + "main 执行结束");
        System.in.read();
    }

    public void mainThreadExecuteWhenCompleted() throws IOException, InterruptedException {
        CompletableFuture<Integer> future = new CompletableFuture<Integer>();
        log.info(Thread.currentThread().getName() + "main 执行开始");

        new Thread(() -> {
            // 子线程A启动
            log.info(Thread.currentThread().getName() + "子线程A启动");
            try {
                log.info(Thread.currentThread().getName() + "子线程A沉睡2s");
                Thread.sleep(2000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info(Thread.currentThread().getName() + "子线程A令future完成");

            // 当子线程A执行到f.complete的时候，会去看是否有注册好的f的then或者when（非async的），如果有的话，会顺次去执行。
            future.complete(100);

            log.info(Thread.currentThread().getName() + "子线程A结束");
        }).start();


        TimeUnit.SECONDS.sleep(3);
        // 当前线程（主线程）执行到这里的时候，如果子线程还没有执行到f.complete(100)，
        // 那么当前线程会把whenComplete事件注册起来，并且说好哪个线程执行了f.complete(100)，
        // 哪个线程就负责执行whenComplete的内容。
        // 如果当前线程（主线程）执行到这里的时候，f.complete(100)已经被其他线程执行完毕了。
        // 那么只有当前线程自己来执行whenComplete里面的内容了。

        future.whenComplete((i, ex) -> {
            // 这个场景下，whenComplete的回调的执行线程会是子线程A
            log.info(Thread.currentThread().getName() + "complete 结束后 , 开始做其他事情");
            try {
                log.info(Thread.currentThread().getName() + "沉睡10s");
                Thread.sleep(1000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info(Thread.currentThread().getName() + "其他事情完成");
        });


        log.info(Thread.currentThread().getName() + "main 执行结束");
        System.in.read();
    }
}
