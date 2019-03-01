package com.paazl.provider;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Provider {
    private final RabbitTemplate rabbitTemplate;

    public void sendMesasge(String message) {
        rabbitTemplate.convertAndSend("paazl-exchange", "paazl.binding.test", message);
    }

    public void sendObject(Object message) {
        rabbitTemplate.convertAndSend("paazl-exchange", "paazl.binding.message", message);
    }

    public void sendToTopic(String message) {
        if (Math.random() > 0.5) {
            rabbitTemplate.convertAndSend("paazl-topic", "paazl.log.error", message);
        }else {
            rabbitTemplate.convertAndSend("paazl-topic", "paazl.log.notification", message);
        }
    }
}
