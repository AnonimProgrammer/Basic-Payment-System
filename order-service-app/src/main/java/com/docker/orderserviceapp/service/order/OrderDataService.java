package com.docker.orderserviceapp.service.order;

import com.docker.orderserviceapp.exceptions.OrderNotFoundException;
import com.docker.orderserviceapp.model.Order;
import com.docker.orderserviceapp.service.messaging.MessagingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDataService {

    private final OrderRepository orderRepository;
    private final MessagingService messagingService;

    public OrderDataService(OrderRepository orderRepository, MessagingService messagingService) {
        this.orderRepository = orderRepository;
        this.messagingService = messagingService;
    }

    // Create
    public Order createOrder(Order order) {
        Order savedOrder = orderRepository.save(order);
        messagingService.publishOrderCreationMessage(savedOrder);

        return savedOrder;
    }

    // Get
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    public Order getOrderByID(Long id) {
        return orderRepository.findById(id).
                orElseThrow(() -> new OrderNotFoundException("Order with such ID is not found!"));
    }

    // Update
    public Order updateOrderByID(Long id, Order order) {
        Order existintgOrder = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order with such ID is not found!"));
        if(existintgOrder != null) {
            existintgOrder.setTotalAmount(order.getTotalAmount());
            return orderRepository.save(existintgOrder);
        }
        return null;
    }

    // Delete
    public void deleteOrderByID(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new OrderNotFoundException("Order with such ID is not found!");
        }
        orderRepository.deleteById(id);
    }



}
