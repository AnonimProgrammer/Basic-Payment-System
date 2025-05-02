package com.docker.orderserviceapp.DTO;

import com.docker.orderserviceapp.model.PaymentStatus;

public class PaymentEventDTO {

    private Long orderID;
    private PaymentStatus paymentStatus;

    public PaymentEventDTO() {}
    public PaymentEventDTO(Long orderID, PaymentStatus paymentStatus) {
        this.orderID = orderID;
        this.paymentStatus = paymentStatus;
    }

    public Long getOrderID() {
        return orderID;
    }
    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }
    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
