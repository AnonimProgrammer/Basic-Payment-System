package com.docker.paymentserviceapp.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue
    private UUID paymentID;

    @Column(nullable = false)
    private Long orderID;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    public Payment() {}

    // Getters
    public UUID getPaymentID() {
        return paymentID;
    }
    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }
    public Long getOrderID() {
        return orderID;
    }

    // Setters
    public void setPaymentID(UUID paymentID) {
        this.paymentID = paymentID;
    }
    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }
    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

}
