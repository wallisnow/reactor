package com.example.reactor.spring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@ConfigurationPropertiesScan
public class ConfigurationPropertiesTest {

    @Autowired
    public AppProperties appProperties;

    @Test
    void testPropertiesValue() {
        List<String> roles = appProperties.getRoles();
        Assertions.assertEquals(roles.get(0),"USER");
    }
}
