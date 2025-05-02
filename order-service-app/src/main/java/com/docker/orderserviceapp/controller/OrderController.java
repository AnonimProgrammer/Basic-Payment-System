package com.docker.orderserviceapp.controller;

import com.docker.orderserviceapp.model.Order;
import com.docker.orderserviceapp.service.order.OrderDataService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderDataService orderDataService;

    public OrderController(OrderDataService orderDataService) {
        this.orderDataService = orderDataService;
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderDataService.createOrder(order);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderDataService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order getOrderByID(@PathVariable Long id) {
        return orderDataService.getOrderByID(id);
    }

    @PutMapping("/{id}")
    public Order updateOrderByID(@PathVariable Long id, @RequestBody Order order) {
        return orderDataService.updateOrderByID(id, order);
    }

    @DeleteMapping("/{id}")
    public void deleteOrderByID(@PathVariable Long id) {
        orderDataService.deleteOrderByID(id);
    }

}
