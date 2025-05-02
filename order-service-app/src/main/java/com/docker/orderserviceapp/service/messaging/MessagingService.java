package com.docker.orderserviceapp.service.messaging;

import com.docker.orderserviceapp.DTO.PaymentEventDTO;
import com.docker.orderserviceapp.message_broker.publisher.MessageBrokerPublisher;
import com.docker.orderserviceapp.model.Order;
import org.springframework.stereotype.Service;

@Service
public class MessagingService {

    private final MessageBrokerPublisher messageBrokerPublisher;
    private final MessagingDataService messagingDataService;

    public MessagingService(MessageBrokerPublisher messageBrokerPublisher, MessagingDataService messagingDataService) {
        this.messageBrokerPublisher = messageBrokerPublisher;
        this.messagingDataService = messagingDataService;
    }

    public void publishOrderCreationMessage(Order order) {
        PaymentEventDTO paymentEventDTO = new PaymentEventDTO(order.getOrderID(), order.getPaymentStatus());
        messageBrokerPublisher.publishMessage(paymentEventDTO);
    }

    public void listenToPaymentEvent(PaymentEventDTO paymentEventDTO) {
        messagingDataService.updateOrderStatus(paymentEventDTO.getOrderID(),
                paymentEventDTO.getPaymentStatus());
    }

}
