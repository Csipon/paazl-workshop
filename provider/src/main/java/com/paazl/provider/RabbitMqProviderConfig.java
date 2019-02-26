package com.paazl.provider;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.paazl.provider.RabbitmqProviderApplication.ROUTING_KEY;
import static com.paazl.provider.RabbitmqProviderApplication.QUEUE_NAME;
import static com.paazl.provider.RabbitmqProviderApplication.ROUTING_KEY_MESSAGE;
import static com.paazl.provider.RabbitmqProviderApplication.TEST_QUEUE_NAME;
import static com.paazl.provider.RabbitmqProviderApplication.TOPIC_EXCHANGE_NAME;

@Configuration
public class RabbitMqProviderConfig {

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, false);
    }

    @Bean
    public Queue queueTestMessage() {
        return new Queue(TEST_QUEUE_NAME, false);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(TOPIC_EXCHANGE_NAME);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange()).with(ROUTING_KEY);
    }

    @Bean
    public Binding bindingTestMessage() {
        return BindingBuilder.bind(queueTestMessage()).to(exchange()).with(ROUTING_KEY_MESSAGE);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
