package com.docker.paymentserviceapp.controller;

import com.docker.paymentserviceapp.model.Payment;
import com.docker.paymentserviceapp.service.payment.PaymentDataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentDataService paymentService;

    public PaymentController(PaymentDataService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public List<Payment> getAllOrders() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/{id}")
    public Payment getOrderByID(@PathVariable Long id) {
        return paymentService.getPaymentByID(id);
    }
}