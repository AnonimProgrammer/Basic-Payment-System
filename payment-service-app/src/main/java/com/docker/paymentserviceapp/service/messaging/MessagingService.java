package com.docker.paymentserviceapp.service.messaging;

import com.docker.paymentserviceapp.DTO.PaymentEventDTO;
import com.docker.paymentserviceapp.exceptions.PaymentEventDTOConversionException;
import com.docker.paymentserviceapp.message_broker.publisher.MessageBrokerPublisher;
import com.docker.paymentserviceapp.model.Payment;
import com.docker.paymentserviceapp.model.PaymentStatus;
import org.springframework.stereotype.Service;

@Service
public class MessagingService {

    private final MessagingDataService messagingDataService;
    private final MessageBrokerPublisher messageBrokerPublisher;

    public MessagingService(MessagingDataService messagingDataService, MessageBrokerPublisher messageBrokerPublisher) {
        this.messagingDataService = messagingDataService;
        this.messageBrokerPublisher = messageBrokerPublisher;
    }

    public void handlePaymentEvent(PaymentEventDTO paymentEventDTO) {
        Payment payment = messagingDataService.createPayment(paymentEventDTO.getOrderID());
        paymentEventDTO.setPaymentStatus(payment.getPaymentStatus());
        messageBrokerPublisher.publishMessage(paymentEventDTO);
    }
}
