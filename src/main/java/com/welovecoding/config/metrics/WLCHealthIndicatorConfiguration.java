package com.welovecoding.config.metrics;

import com.welovecoding.api.v1.base.Logged;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.inject.Inject;
import javax.sql.DataSource;

@Configuration
public class WLCHealthIndicatorConfiguration {

    @Inject
    private DataSource dataSource;

    @Logged
    @Bean
    public HealthIndicator dbHealthIndicator() {
        return new DatabaseHealthIndicator(dataSource);
    }
}
