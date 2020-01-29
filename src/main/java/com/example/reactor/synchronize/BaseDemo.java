package com.example.reactor.synchronize;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class BaseDemo {

    AsyncCall asyncCall = new AsyncCall();

    public abstract void callback(long response);

    public void call() {
        log.info("发起调用");
        //实际调用自己的callback()方法
        asyncCall.call(this);
        log.info("返回调用");
    }
}
