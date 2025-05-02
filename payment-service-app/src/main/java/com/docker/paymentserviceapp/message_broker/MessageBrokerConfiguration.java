package com.docker.paymentserviceapp.message_broker;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageBrokerConfiguration {

    @Value("${rabbitmq.order.queue.name}")
    private String paymentQueue;

    @Value("${rabbitmq.order.exchange.name}")
    private String paymentExchange;

    @Value("${rabbitmq.order.routing.key}")
    private String paymentRoutingKey;

    @Bean
    public Queue queue() {
        return new Queue(paymentQueue);
    }
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(paymentExchange);
    }
    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue())
                .to(exchange())
                .with(paymentRoutingKey);
    }
}