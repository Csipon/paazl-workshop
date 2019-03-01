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

@Configuration
public class RabbitMqProviderConfig {

    @Bean
    public Queue queue() {
        return new Queue("paazl-queue", false);
    }

    @Bean
    public Queue queueTestMessage() {
        return new Queue("test-paazl-queue", false);
    }


    @Bean
    public Queue topicQueue_1() {
        return new Queue("paazl-topic-queue_1", false);
    }

    @Bean
    public Queue topicQueue_2() {
        return new Queue("paazl-topic-queue_2", false);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("paazl-exchange");
    }

    @Bean
    public TopicExchange topic() {
        return new TopicExchange("paazl-topic");
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange()).with("paazl.binding.test");
    }

    @Bean
    public Binding bindingTestMessage() {
        return BindingBuilder.bind(queueTestMessage()).to(exchange()).with("paazl.binding.message");
    }

    @Bean
    public Binding topicQueueBinding_1() {
        return BindingBuilder.bind(topicQueue_1()).to(topic()).with("*.log.*");
    }

    @Bean
    public Binding topicQueueBinding_2() {
        return BindingBuilder.bind(topicQueue_2()).to(topic()).with("*.*.notification");
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
