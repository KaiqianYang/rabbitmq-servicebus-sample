package com.example.bindingExchange;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    @Value("${env}")
    private String env;

    @Bean
    public String queue1() {
        return "queueName1"+env;
    }

    @Bean
    public String queue2() {
        return "queueName2"+env;
    }
}
