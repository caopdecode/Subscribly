package com.caopdecode.subscribly.controller;

import com.caopdecode.subscribly.dto.PaymentDTO;
import com.caopdecode.subscribly.dto.PaymentResponse;
import com.caopdecode.subscribly.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<String> processPayment(@RequestBody PaymentDTO paymentDTO){
        try{
            paymentService.processPayment(paymentDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Payment processed successfully");
        } catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<PaymentResponse>> getPayments(@RequestParam String email){
        return ResponseEntity.ok(paymentService.getPaymentsByUser(email));
    }
}
