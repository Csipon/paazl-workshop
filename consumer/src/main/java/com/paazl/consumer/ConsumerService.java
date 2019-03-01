package com.paazl.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class ConsumerService {

    @RabbitListener(queues = RabbitmqConsumerApplication.QUEUE_NAME)
    public void receiveMessage(Message message) {
        log.info("[{}] ---- Simple message: <{}>", 0, message.toString());
    }

    @RabbitListener(queues = RabbitmqConsumerApplication.TEST_QUEUE_NAME)
    public void receiveMessage(TestMessage message) {
        log.info("[{}] ++++ Custom object message: <{}>", 1, message.toString());
    }

    @RabbitListener(queues = "paazl-topic-queue_1")
    public void receiveLogMessage(Message message) {
        log.info("[{}] ==== Log message: <{}>", 2, message.toString());
    }

    @RabbitListener(queues = "paazl-topic-queue_2")
    public void receiveNotificationMessage(Message message) {
        log.info("[{}] >>>> Notification message: <{}>", 3, message.toString());
    }

}
