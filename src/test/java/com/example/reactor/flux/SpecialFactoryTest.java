package com.example.reactor.flux;

import com.example.reactor.SpecialFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import reactor.core.publisher.Flux;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SpecialFactoryTest {

    @Test
    @Order(1)
    void testEmpty() {
        log.info(" test Flux Empty ");
        Flux.empty().subscribe(new SpecialFactory());
    }

    @Test
    @Order(2)
    void testError() {
        log.info(" test Flux Error ");
        Flux.error(new RuntimeException("~ ~ my exception")).subscribe(new SpecialFactory());
    }

    @Test
    @Order(3)
    void testNever() {
        log.info(" test Flux Never ");
        Flux.never().subscribe(new SpecialFactory());
    }
}
