package com.paazl.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class ConsumerService {

    @RabbitListener(queues = RabbitmqConsumerApplication.QUEUE_NAME)
    public void receiveMessage(Message message){
        log.info("---- Simple message: <{}>", message.toString());
    }

    @RabbitListener(queues = RabbitmqConsumerApplication.TEST_QUEUE_NAME)
    public void receiveMessage(TestMessage message){
        log.info("++++ Custom object message: <{}>", message.toString());
    }
}
