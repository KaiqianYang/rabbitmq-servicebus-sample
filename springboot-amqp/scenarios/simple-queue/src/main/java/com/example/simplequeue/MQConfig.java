package com.example.simplequeue;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {
    @Bean
    public Queue simpleQueue() {
        return new Queue("simpleQueue", false);
    }

    @Bean
    public String simpleQueueForServicebus() {
        return new String("simpleQueue");
    }
}
