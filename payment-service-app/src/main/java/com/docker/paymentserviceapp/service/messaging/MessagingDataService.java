package com.docker.paymentserviceapp.service.messaging;

import com.docker.paymentserviceapp.model.Payment;
import com.docker.paymentserviceapp.model.PaymentStatus;
import com.docker.paymentserviceapp.service.payment.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class MessagingDataService {

    private final PaymentRepository paymentRepository;

    public MessagingDataService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment createPayment(Long orderID) {
        Payment payment = new Payment();
        payment.setOrderID(orderID);
        payment.setPaymentStatus(PaymentStatus.PAID);

        return paymentRepository.save(payment);
    }
}
