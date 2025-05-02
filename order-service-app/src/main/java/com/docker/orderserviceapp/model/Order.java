package com.docker.orderserviceapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    @SequenceGenerator(
            name = "order_id_seq",
            sequenceName = "order_id_seq",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_id_seq")
    private Long orderID;

    @Column(nullable = false)
    private double totalAmount;

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus = PaymentStatus.NOT_PAID;

    public Order() {}

    // Getters
    public Long getOrderID() {
        return orderID;
    }
    public Status getStatus() {
        return status;
    }
    public double getTotalAmount() {
        return totalAmount;
    }
    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    // Setters
    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
