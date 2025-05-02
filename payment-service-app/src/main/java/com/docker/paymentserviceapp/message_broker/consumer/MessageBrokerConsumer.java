package com.docker.paymentserviceapp.message_broker.consumer;

import com.docker.paymentserviceapp.DTO.PaymentEventDTO;
import com.docker.paymentserviceapp.exceptions.InvalidPaymentEventDTOInputException;
import com.docker.paymentserviceapp.service.messaging.MessagingService;
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

    @RabbitListener(queues = "${rabbitmq.payment.queue.name}")
    public void listenToPaymentEvent(String paymentEventDTOjson){
        PaymentEventDTO paymentEventDTO = convertJsonToDTO(paymentEventDTOjson);
        messagingService.handlePaymentEvent(paymentEventDTO);
    }

    private PaymentEventDTO convertJsonToDTO(String paymentEventDTOjson) {
        try {
            return new ObjectMapper().readValue(paymentEventDTOjson, PaymentEventDTO.class);
        } catch (JsonProcessingException exception) {
            throw new InvalidPaymentEventDTOInputException("Invalid PaymentEventDTO input", exception);
        }
    }
}
