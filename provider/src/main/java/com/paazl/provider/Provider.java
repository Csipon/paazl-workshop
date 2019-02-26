package com.paazl.provider;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import static com.paazl.provider.RabbitmqProviderApplication.TOPIC_EXCHANGE_NAME;

@Component
@RequiredArgsConstructor
public class Provider {
    private final RabbitTemplate rabbitTemplate;

    public void sendMesasge(String message){
        rabbitTemplate.convertAndSend(TOPIC_EXCHANGE_NAME, "paazl.binding.test" , message);
    }

    public void sendObject(Object message){
        rabbitTemplate.convertAndSend(TOPIC_EXCHANGE_NAME, "paazl.binding.message" , message);
    }
}
