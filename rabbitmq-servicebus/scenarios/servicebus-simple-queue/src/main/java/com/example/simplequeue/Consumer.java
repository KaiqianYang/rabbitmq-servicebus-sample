package com.example.simplequeue;

import com.azure.spring.messaging.servicebus.implementation.core.annotation.ServiceBusListener;
import com.azure.messaging.servicebus.ServiceBusException;


public class Consumer {

    @ServiceBusListener(destination = "queueName")
    public void consumeMessage(String message) {
        System.out.println("Received message: " + message);
    }

    @ServiceBusListener(destination = "#{queue}")
    public void consumeMessageWithDynamicQueueName(String message) {
        System.out.println("Received message: " + message);
    }

    @ServiceBusListener(destination = "#{queue}")
    public void consumeMessageWithException(String message) {
        try {
            handle(message);
        } catch (Exception e) {
            throw new ServiceBusException(e, null);
        }
    }

    public void handle(String message) {
        System.out.println("Received message: " + message);
    }


}
