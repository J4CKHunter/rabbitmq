package com.erdemnayin.rabbitmq.controller;

import com.erdemnayin.rabbitmq.config.RabbitMqTopicExchangeConfig;
import com.erdemnayin.rabbitmq.model.CustomMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/customMessage")
@Profile("topic")
public class TopicExchangeProducerController {

    @Value("${sample.rabbitmq.exchange}")
    public String topicExchange;

    private final RabbitTemplate template;

    public TopicExchangeProducerController(RabbitTemplate template) {
        this.template = template;
    }

    @PostMapping("/queueA")
    public String publishMessageToQueueA(@RequestBody CustomMessage message){

        template.convertAndSend(topicExchange, "message.a.test", message);

        return "Message published successfully: \n" + message;
    }

    @PostMapping("/queueB")
    public String publishMessageToQueueB(@RequestBody CustomMessage message){

        template.convertAndSend(topicExchange, "message.queue.b.test", message);

        return "Message published successfully: \n" + message;
    }
}
