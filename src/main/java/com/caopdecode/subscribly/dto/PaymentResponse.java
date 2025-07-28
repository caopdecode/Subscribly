package com.caopdecode.subscribly.dto;

import com.caopdecode.subscribly.model.PaymentStatus;

import java.time.LocalDateTime;

public class PaymentResponse {
    private String planName;
    private double amout;
    private LocalDateTime paymentDate;
    private String transactionId;
    private PaymentStatus status;

    public PaymentResponse(String planName, double amout, LocalDateTime paymentDate, String transactionId, PaymentStatus status) {
        this.planName = planName;
        this.amout = amout;
        this.paymentDate = paymentDate;
        this.transactionId = transactionId;
        this.status = status;
    }

    public String getPlanName() {
        return planName;
    }

    public double getAmout() {
        return amout;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public PaymentStatus getStatus() {
        return status;
    }
}
