package com.docker.paymentserviceapp.DTO;

import com.docker.paymentserviceapp.model.PaymentStatus;

public class PaymentEventDTO {

    private Long orderID;
    private PaymentStatus paymentStatus;

    public PaymentEventDTO() {}
    public PaymentEventDTO(Long orderID, PaymentStatus paymentStatus) {
        this.orderID = orderID;
        this.paymentStatus = paymentStatus;
    }

    // Getters
    public Long getOrderID() {
        return orderID;
    }
    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    // Setters
    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }
    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
