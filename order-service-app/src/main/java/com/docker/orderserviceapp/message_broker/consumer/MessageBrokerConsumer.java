package com.docker.orderserviceapp.message_broker.consumer;

import com.docker.orderserviceapp.DTO.PaymentEventDTO;
import com.docker.orderserviceapp.exceptions.InvalidPaymentEventDTOInputException;
import com.docker.orderserviceapp.service.messaging.MessagingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageBrokerConsumer {

    private final MessagingService messagingService;

    public MessageBrokerConsumer(MessagingService messagingService) {
        this.messagingService = messagingService;
    }

    @RabbitListener(queues = "${rabbitmq.order.queue.name}")
    public void listenToPaymentEvent(String paymentEventDTOjson){
        PaymentEventDTO paymentEventDTO = convertJsonToDTO(paymentEventDTOjson);
        messagingService.listenToPaymentEvent(paymentEventDTO);
    }

    private PaymentEventDTO convertJsonToDTO(String paymentEventDTOjson) {
        try {
            return new ObjectMapper().readValue(paymentEventDTOjson, PaymentEventDTO.class);
        } catch (JsonProcessingException exception) {
            throw new InvalidPaymentEventDTOInputException("Invalid PaymentEventDTO input", exception);
        }
    }
}
