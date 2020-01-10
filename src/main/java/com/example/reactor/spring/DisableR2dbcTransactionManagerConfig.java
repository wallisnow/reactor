package com.example.reactor.spring;

import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@ActiveProfiles("r2dbc.tm.disabled")
public @interface DisableR2dbcTransactionManagerConfig {
}
