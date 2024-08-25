package com.example.bindingExchange;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    @Value("${env}")
    private String env;

    @Bean
    public Queue queue1() {
        return new Queue("queueName1"+env, false);
    }

    @Bean
    public Queue queue2() {
        return new Queue("queueName2"+env, false);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("exchangeName");
    }

    @Bean
    public Binding binding1(DirectExchange directExchange, Queue queue1) {
        return BindingBuilder.bind(queue1).to(directExchange).with("routingKey1");
    }

    @Bean
    public Binding binding2(DirectExchange directExchange, Queue queue1) {
        return BindingBuilder.bind(queue1).to(directExchange).with("routingKey2");
    }

    @Bean
    public Binding binding3(DirectExchange directExchange, Queue queue2) {
        return BindingBuilder.bind(queue2).to(directExchange).with("routingKey2");
    }
}
