package com.example.simplequeue;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    @Value("${env}")
    private String env;

    @Bean
    public String queue() {
        return "queueName"+env;
    }
}
