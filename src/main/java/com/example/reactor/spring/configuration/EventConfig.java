package com.example.reactor.spring.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
@Slf4j
public class EventConfig {

    @EventListener(ApplicationStartedEvent.class)
    void init(){
        log.info("init Event config");
    }
}
