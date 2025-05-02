package com.docker.paymentserviceapp.service.payment;

import com.docker.paymentserviceapp.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
