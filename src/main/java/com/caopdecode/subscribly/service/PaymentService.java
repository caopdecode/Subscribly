package com.caopdecode.subscribly.service;

import com.caopdecode.subscribly.dto.PaymentDTO;
import com.caopdecode.subscribly.dto.PaymentResponse;

import java.util.List;

public interface PaymentService {
    void processPayment(PaymentDTO paymentDTO);
    List<PaymentResponse> getPaymentsByUser(String email);
}
