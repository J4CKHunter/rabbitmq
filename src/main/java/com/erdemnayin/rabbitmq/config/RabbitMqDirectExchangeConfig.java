package com.erdemnayin.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("direct")
//@ConditionalOnProperty(prefix = "rabbitmq.exchange", name = "type", havingValue = "direct")
public class RabbitMqDirectExchangeConfig {

    public static final String QUEUE_A = "queue-a";
    public static final String QUEUE_B = "queue-b";
    @Value("${sample.rabbitmq.exchange}")
    public String directExchange;

    @Value("${sample.rabbitmq.routing-key.queue-a}")
    public String routingKeyQueueA;

    @Value("${sample.rabbitmq.routing-key.queue-b}")
    public String routingKeyQueueB;


    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(directExchange);
    }

    @Bean
    public Binding bindingQueueA(Queue queueA, DirectExchange directExchange){
        return BindingBuilder
                .bind(queueA)
                .to(directExchange)
                .with(routingKeyQueueA);
    }

    @Bean
    public Binding bindingQueueB(Queue queueB, DirectExchange directExchange){
        return BindingBuilder
                .bind(queueB)
                .to(directExchange)
                .with(routingKeyQueueB);
    }
}
