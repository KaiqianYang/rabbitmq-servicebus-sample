package com.example.simplequeue;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    @Value("${env}")
    private String env;

    @Bean
    public Queue queue() {
        return new Queue("queueName"+env, false);
    }
}
