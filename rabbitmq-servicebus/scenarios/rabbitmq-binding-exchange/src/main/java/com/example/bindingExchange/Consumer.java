package com.example.bindingExchange;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class Consumer {

    @RabbitListener(queues = "#{queue1}")
    public void consumeMessage1(String message) {
        System.out.println("Received message: " + message);
    }

    @RabbitListener(queues = "#{queue2}")
    public void consumeMessage2(String message) {
        System.out.println("Received message: " + message);
    }


}
