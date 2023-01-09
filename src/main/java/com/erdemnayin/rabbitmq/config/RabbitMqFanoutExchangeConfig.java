package com.erdemnayin.rabbitmq.config;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Binding;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("fanout")
//@ConditionalOnProperty(prefix = "rabbitmq.exchange", name = "type", havingValue = "fanout")
public class RabbitMqFanoutExchangeConfig {

    @Value("${sample.rabbitmq.exchange}")
    public String fanoutExchange;

    @Bean
    public FanoutExchange exchange(){
        return new FanoutExchange(fanoutExchange);
    }
    @Bean
    public Binding bindingQueueA(Queue queueA, FanoutExchange fanoutExchange){
        return BindingBuilder
                .bind(queueA)
                .to(fanoutExchange);
    }

    @Bean
    public Binding bindingQueueB(Queue queueB, FanoutExchange fanoutExchange){
        return BindingBuilder
                .bind(queueB)
                .to(fanoutExchange);
    }


}
