package com.example.simplequeue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.support.MessageBuilder;
import com.azure.spring.messaging.servicebus.core.ServiceBusTemplate;


public class Producer {

    @Autowired
    private final ServiceBusTemplate serviceBusTemplate;

    @Value("${env}")
    private String env;

    public Producer( ServiceBusTemplate serviceBusTemplate) {
        this.serviceBusTemplate = serviceBusTemplate;
    }

    public void sendMessage() {
        serviceBusTemplate.send("queueName"+env, MessageBuilder.withPayload("Hello, world!").build());
    }

    public void sendMessageWithProperties() {
        org.springframework.messaging.Message<String> message = MessageBuilder.withPayload("Hello, world!")
                .setHeader("customHeader", "headerValue")
                .setHeader("contentType", "application/json")
                .build();
        serviceBusTemplate.send("queueName"+env, message);
    }



}
