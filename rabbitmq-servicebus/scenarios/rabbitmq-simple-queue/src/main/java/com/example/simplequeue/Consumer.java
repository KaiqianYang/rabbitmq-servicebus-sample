package com.example.simplequeue;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;

public class Consumer {

    @Value("${env}")
    private String env;

    @RabbitListener(queues = "queueName")
    public void consumeMessage(String message) {
        System.out.println("Received message: " + message);
    }

    @RabbitListener(queues = "#{queue}")
    public void consumeMessageWithDynamicQueueName(String message) {
        System.out.println("Received message: " + message);
    }

    @RabbitListener(queues = "#{queue}")
    public void consumeMessageWithException(String message) {
        try {
            handle(message);
        } catch (Exception e) {
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }

    public void handle(String message) {
        System.out.println("Received message: " + message);
    }


}
