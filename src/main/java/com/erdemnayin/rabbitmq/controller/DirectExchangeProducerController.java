package com.erdemnayin.rabbitmq.controller;

import com.erdemnayin.rabbitmq.config.RabbitMqConfig;
import com.erdemnayin.rabbitmq.config.RabbitMqDirectExchangeConfig;
import com.erdemnayin.rabbitmq.model.CustomMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
@Profile("direct")
public class DirectExchangeProducerController {

    @Value("${sample.rabbitmq.exchange}")
    public String directExchange;

    @Value("${sample.rabbitmq.routing-key.queue-a}")
    public String routingKeyQueueA;

    @Value("${sample.rabbitmq.routing-key.queue-b}")
    public String routingKeyQueueB;
    private final RabbitTemplate template;

    public DirectExchangeProducerController(RabbitTemplate template) {
        this.template = template;
    }

    @PostMapping("/customMessage")
    public String publishMessage(@RequestParam String queue, @RequestBody CustomMessage message){

        if(queue.equals(RabbitMqDirectExchangeConfig.QUEUE_A)){
            template.convertAndSend(directExchange, routingKeyQueueA, message);
        }else if (queue.equals(RabbitMqDirectExchangeConfig.QUEUE_B)){
            template.convertAndSend(directExchange, routingKeyQueueB, message);
        }else{
            return "Invalid queue name.";
        }

        System.out.println("Message published successfully: \n" + message);
        return "Message published successfully: \n" + message;
    }
}
