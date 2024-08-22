package com.example.simplequeue;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import com.azure.spring.messaging.servicebus.implementation.core.annotation.ServiceBusListener;
import com.azure.messaging.servicebus.ServiceBusException;


public class Consumer {

    //before
    @RabbitListener(queues = "simpleQueue")
    public void consumeMessage(String message) {
        System.out.println("Received message: " + message);
    }
    //after
    @ServiceBusListener(destination = "#{simpleQueueForServicebus}")
    public void consumeMessageFromServiceBus(String message) {
        System.out.println("Received message: " + message);
    }

    //before
    @RabbitListener(queues = "#{simpleQueue}")
    public void consumeMessage2(String message) {
        System.out.println("Received message: " + message);
    }
    //after
    @ServiceBusListener(destination = "#{simpleQueueForServicebus}")
    public void consumeMessageFromServiceBus2(String message) {
        System.out.println("Received message: " + message);
    }
    //before
    @RabbitListener(queues = "#{simpleQueue}")
    public void consumeMessageWithException(String message) {
        try {
            handle(message);
        } catch (Exception e) {
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
    //after
    @ServiceBusListener(destination = "#{simpleQueueForServicebus}")
    public void consumeMessageFromServiceBusWithException(String message) {
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
