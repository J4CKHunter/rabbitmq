package com.erdemnayin.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("headers")
//@ConditionalOnProperty(prefix = "rabbitmq.exchange", name = "type", havingValue = "headers")
public class RabbitMqHeadersExchangeConfig {

    @Value("${sample.rabbitmq.exchange}")
    private String headersExchange;

    @Bean
    public HeadersExchange exchange(){
        return new HeadersExchange(headersExchange);
    }

    @Bean
    public Binding bindingQueueA(Queue queueA, HeadersExchange headersExchange){
        return BindingBuilder
                .bind(queueA)
                .to(headersExchange)
                .where("type")
                .matches("urgent");
    }

    @Bean
    public Binding bindingQueueB(Queue queueB, HeadersExchange headersExchange){
        return BindingBuilder
                .bind(queueB)
                .to(headersExchange)
                .where("type")
                .matches("important");
    }
}
