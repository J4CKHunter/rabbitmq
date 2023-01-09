package com.erdemnayin.rabbitmq.controller;

import com.erdemnayin.rabbitmq.config.RabbitMqHeadersExchangeConfig;
import com.erdemnayin.rabbitmq.model.CustomMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@Profile("headers")
public class HeadersExchangeProducerController {

    private final RabbitTemplate template;

    public HeadersExchangeProducerController(RabbitTemplate template) {
        this.template = template;
    }

    @Value("${sample.rabbitmq.exchange}")
    public String headersExchange;

    @PostMapping("/customMessage")
    public String publishMessage(@RequestParam String type,
                                 @RequestBody CustomMessage customMessage){


        template.convertAndSend(
                headersExchange,
                "" ,
                customMessage,
                message -> {
                    message.getMessageProperties().setHeader("type", type);
                    return message;
                }
        );

        return "Message published successfully: \n" + customMessage;
    }

}
