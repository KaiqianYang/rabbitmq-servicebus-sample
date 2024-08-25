package com.example.bindingExchange;

import com.azure.spring.messaging.servicebus.implementation.core.annotation.ServiceBusListener;
import com.azure.messaging.servicebus.ServiceBusException;


public class Consumer {

    @ServiceBusListener(destination = "#{queue1}")
    public void consumeMessage1(String message) {
        System.out.println("Received message: " + message);
    }

    @ServiceBusListener(destination = "#{queue2}")
    public void consumeMessage2(String message) {
        System.out.println("Received message: " + message);
    }

}
