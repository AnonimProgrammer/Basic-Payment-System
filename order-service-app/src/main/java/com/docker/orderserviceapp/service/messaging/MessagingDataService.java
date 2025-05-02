package com.docker.orderserviceapp.service.messaging;

import com.docker.orderserviceapp.exceptions.OrderNotFoundException;
import com.docker.orderserviceapp.model.Order;
import com.docker.orderserviceapp.model.PaymentStatus;
import com.docker.orderserviceapp.model.Status;
import com.docker.orderserviceapp.service.order.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class MessagingDataService {

    private final OrderRepository orderRepository;

    public MessagingDataService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void updateOrderStatus(Long orderID, PaymentStatus paymentStatus) {
        Order order = orderRepository.findById(orderID)
                .orElseThrow(() -> new OrderNotFoundException("Order with such ID is not found!"));
        if(order != null) {
            order.setPaymentStatus(paymentStatus);
            Status status = (paymentStatus == PaymentStatus.PAID) ? Status.CONFIRMED : Status.FAILED;
            order.setStatus(status);

            orderRepository.save(order);
        }
    }
}
