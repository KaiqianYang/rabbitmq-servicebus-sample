package com.example.simplequeue;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.messaging.support.MessageBuilder;

import com.azure.spring.messaging.servicebus.core.ServiceBusTemplate;


public class Producer {
    @Autowired
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    private final ServiceBusTemplate serviceBusTemplate;

    public Producer(RabbitTemplate rabbitTemplate, ServiceBusTemplate serviceBusTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.serviceBusTemplate = serviceBusTemplate;
    }

    //before
    public void sendMessage() {
        rabbitTemplate.convertAndSend("simpleQueue", "Hello, world!");
    }
    //after
    public void sendMessageToServiceBus() {
        serviceBusTemplate.send("simpleQueue", MessageBuilder.withPayload("Hello, world!").build());
    }

    //before
    public void sendMessageWithProperties() {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("customHeader", "headerValue");
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_JSON);
        Message rabbitMessage = new Message("Hello, world!".getBytes(), messageProperties);
        rabbitTemplate.convertAndSend("simpleQueue", rabbitMessage);
    }

    //after
    public void sendMessageToServiceBusWithProperties() {
        org.springframework.messaging.Message<String> message = MessageBuilder.withPayload("Hello, world!")
                .setHeader("customHeader", "headerValue")
                .setHeader("contentType", "application/json")
                .build();
        serviceBusTemplate.send("simpleQueue", message);
    }



}
