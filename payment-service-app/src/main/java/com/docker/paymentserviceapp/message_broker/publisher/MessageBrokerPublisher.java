package com.docker.paymentserviceapp.message_broker.publisher;

import com.docker.paymentserviceapp.DTO.PaymentEventDTO;
import com.docker.paymentserviceapp.exceptions.PaymentEventDTOConversionException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MessageBrokerPublisher {

    @Value("${rabbitmq.order.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.order.routing.key}")
    private String routingKey;

    private final static Logger logger = LoggerFactory.getLogger(MessageBrokerPublisher.class);
    private RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    public MessageBrokerPublisher(ObjectMapper objectMapper, RabbitTemplate rabbitTemplate) {
        this.objectMapper = objectMapper;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishMessage(PaymentEventDTO paymentEventDTO) {
        logger.info("Publishing message to exchange %s.", exchange);
        try {
            String paymentEventDTOjson = objectMapper.writeValueAsString(paymentEventDTO);
            rabbitTemplate.convertAndSend(exchange, routingKey, paymentEventDTOjson);

        } catch (JsonProcessingException exception) {
            throw new PaymentEventDTOConversionException("Error while converting PaymentEventDTO object to JSON format.",
                    exception);
        }
    }
}
