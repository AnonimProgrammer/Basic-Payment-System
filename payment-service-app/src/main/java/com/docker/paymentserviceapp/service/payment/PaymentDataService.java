package com.docker.paymentserviceapp.service.payment;

import com.docker.paymentserviceapp.exceptions.PaymentNotFoundException;
import com.docker.paymentserviceapp.model.Payment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentDataService {

    private final PaymentRepository paymentRepository;

    public PaymentDataService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPaymentByID(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found with id: " + id));
    }
}
