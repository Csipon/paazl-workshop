package com.paazl.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RabbitmqProviderApplication {
    public static final String TOPIC_EXCHANGE_NAME = "paazl-exchange";
    public static final String QUEUE_NAME = "paazl-queue";
    public static final String TEST_QUEUE_NAME = "test-paazl-queue";
    public static final String ROUTING_KEY = "paazl.binding.test";
    public static final String ROUTING_KEY_MESSAGE = "paazl.binding.message";

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqProviderApplication.class, args);
    }

}
