package com.erdemnayin.rabbitmq.Service;

import com.erdemnayin.rabbitmq.config.RabbitMqConfig;
import com.erdemnayin.rabbitmq.model.CustomMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Listener {

        /*
    @RabbitListener(queues = {MQConfig.QUEUE_A, MQConfig.QUEUE_B})
    public void listener(CustomMessage message){
        System.out.println(message);
    }
    */


    @RabbitListener(queues = RabbitMqConfig.QUEUE_A)
    public void listenerQueueA(CustomMessage message){
        log.info("Message received from QueueA -> {}", message);
    }

    @RabbitListener(queues = RabbitMqConfig.QUEUE_B)
    public void listenerQueueB(CustomMessage message){
        log.info("Message received from QueueB -> {}", message);
    }
}
