package com.erdemnayin.rabbitmq.controller;

import com.erdemnayin.rabbitmq.config.RabbitMqFanoutExchangeConfig;
import com.erdemnayin.rabbitmq.model.CustomMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@Profile("fanout")
public class FanoutExchangeProducerController {

    @Value("${sample.rabbitmq.exchange}")
    public String fanoutExchange;

    private final RabbitTemplate template;

    public FanoutExchangeProducerController(RabbitTemplate template) {
        this.template = template;
    }

    @PostMapping("/customMessage")
    public String publishMessage(@RequestBody CustomMessage message){

        template.convertAndSend(fanoutExchange,"",message);

        return "Message published successfully: \n" + message;
    }

}
