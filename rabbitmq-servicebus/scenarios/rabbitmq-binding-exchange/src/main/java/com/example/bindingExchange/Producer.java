package com.example.bindingExchange;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class Producer {
    @Autowired
    private final RabbitTemplate rabbitTemplate;

    @Autowired
	private DirectExchange directExchange;


    public Producer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage1() {
        rabbitTemplate.convertAndSend(directExchange.getName(),"routingKey1", "1: Hello, world!");
    }
    
    public void sendMessage2() {
        rabbitTemplate.convertAndSend(directExchange.getName(),"routingKey2", "2: Hello, world!");
    }

}
