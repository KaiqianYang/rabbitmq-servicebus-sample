package com.example.simplequeue;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class Producer {
    @Autowired
    private final RabbitTemplate rabbitTemplate;

    @Value("${env}")
    private String env;

    public Producer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage() {
        rabbitTemplate.convertAndSend("queueName"+env, "Hello, world!");
    }

    public  void sendMessageWithSetRoutingKey() {
        rabbitTemplate.setRoutingKey("queueName"+env);
        rabbitTemplate.convertAndSend("Hello, world!");
    }

    public void sendMessageWithProperties() {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("customHeader", "headerValue");
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_JSON);
        Message rabbitMessage = new Message("Hello, world!".getBytes(), messageProperties);
        rabbitTemplate.convertAndSend("queueName"+env, rabbitMessage);
    }

    public void sendMessageWithMessageBuilder() {
        Message rabbitMessage = MessageBuilder.withBody("Hello, world!".getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .setMessageId("123")
                .setHeader("customHeader", "headerValue")
                .build();
        rabbitTemplate.send("queueName"+env, rabbitMessage);
    }

}
