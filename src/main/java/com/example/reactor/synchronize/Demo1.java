package com.example.reactor.synchronize;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Demo1 extends BaseDemo {

    private final Object lock = new Object();

    @Override
    public void callback(long response) {
        log.info("Thread: {} 调用并得到 response: {}", Thread.currentThread().getName(), response);

        // 异步
        // 回调函数被调用时，代表服务端处理请求已经结束， 释放锁
        synchronized (lock) {
            lock.notifyAll();
        }
    }

    public static void main(String[] args) {
        Demo1 demo1 = new Demo1();

        demo1.call();

        synchronized (demo1.lock) {
            try {
                // 异步
                // 开始线程上锁，等待上面的lock.notifyAll()被调用
                demo1.lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("主线程内容");
    }
}
