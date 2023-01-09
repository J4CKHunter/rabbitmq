package com.erdemnayin.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("topic")
//@ConditionalOnProperty(prefix = "rabbitmq.exchange", name = "type", havingValue = "topic")
public class RabbitMqTopicExchangeConfig {

    @Value("${sample.rabbitmq.exchange}")
    public String topicExchange;

    @Value("${sample.rabbitmq.routing-key.queue-a}")
    public String routingKeyQueueA;

    @Value("${sample.rabbitmq.routing-key.queue-b}")
    public String routingKeyQueueB;


    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(topicExchange);
    }

    @Bean
    public Binding bindingQueueA(Queue queueA, TopicExchange topicExchange){
        return BindingBuilder
                .bind(queueA)
                .to(topicExchange)
                .with(routingKeyQueueA);
    }

    @Bean
    public Binding bindingQueueB(Queue queueB, TopicExchange topicExchange){
        return BindingBuilder
                .bind(queueB)
                .to(topicExchange)
                .with(routingKeyQueueB);
    }

}
