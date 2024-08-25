package com.example.bindingExchange;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import com.azure.spring.messaging.servicebus.core.ServiceBusTemplate;


public class Producer {

    @Autowired
    private final ServiceBusTemplate serviceBusTemplate;

    @Autowired
    private String queue1;

    @Autowired
    private String queue2;

    public Producer( ServiceBusTemplate serviceBusTemplate) {
        this.serviceBusTemplate = serviceBusTemplate;
    }

    public void sendMessage1() {
        serviceBusTemplate.send(queue1, MessageBuilder.withPayload("1: Hello, world!").build());
    }

    public void sendMessage12() {
        serviceBusTemplate.send(queue1, MessageBuilder.withPayload("2: Hello, world!").build());
    }

    public void sendMessage2() {
        serviceBusTemplate.send(queue2, MessageBuilder.withPayload("2: Hello, world!").build());
    }



}
