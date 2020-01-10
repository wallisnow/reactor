package com.example.reactor.spring;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DurationUnit;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@ConfigurationProperties(prefix = "app")
@NoArgsConstructor
public class AppProperties {
    private String name;
    private String description;
    private String uploadDir;
    private Duration connectTimeout = Duration.ofMillis(1000);
    @DurationUnit(ChronoUnit.SECONDS)
    private Duration readTimeout = Duration.ofSeconds(30);
    private String username;
    private String password;
    private List<String> roles;
    private boolean enabled;
    private Map<String, String> permissions = new HashMap<>();

}
