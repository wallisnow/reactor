package com.example.reactor;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

@Slf4j
public class SpecialFactory implements Subscriber {

    @Override
    public void onSubscribe(Subscription subscription) {
        log.info("on Subscribe");
        subscription.request(1);
    }

    @Override
    public void onNext(Object o) {
        log.info("on next value is" + o);
    }

    @Override
    public void onError(Throwable throwable) {
        log.info("onError exception message is " + throwable.getMessage());
    }

    @Override
    public void onComplete() {
        log.info("on Complete");
    }
}
