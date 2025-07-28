package com.caopdecode.subscribly.serviceimpl;

import com.caopdecode.subscribly.dto.PaymentDTO;
import com.caopdecode.subscribly.dto.PaymentResponse;
import com.caopdecode.subscribly.model.Payment;
import com.caopdecode.subscribly.model.PaymentStatus;
import com.caopdecode.subscribly.model.Subscription;
import com.caopdecode.subscribly.repository.PaymentRepository;
import com.caopdecode.subscribly.repository.SubscriptionRepository;
import com.caopdecode.subscribly.service.PaymentService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final SubscriptionRepository subscriptionRepository;
    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(SubscriptionRepository subscriptionRepository, PaymentRepository paymentRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    public void processPayment(PaymentDTO paymentDTO) {
        Subscription sub = subscriptionRepository.findById(paymentDTO.getSubscriptionId())
                .orElseThrow(() -> new IllegalArgumentException("Subscription not found"));

        if(paymentRepository.existsBySubscriptionIdAndStatus(sub.getId(), PaymentStatus.SUCCESS)){
            throw new IllegalStateException("Subscription already paid");
        }

        Payment payment = new Payment();
        payment.setSubscription(sub);
        payment.setAmount(paymentDTO.getAmout());
        payment.setPaymentDate(LocalDateTime.now());
        payment.setStatus(PaymentStatus.SUCCESS);
        payment.setTransactionId(UUID.randomUUID().toString());

        paymentRepository.save(payment);
    }

    @Override
    public List<PaymentResponse> getPaymentsByUser(String email){
        return paymentRepository.findBySubscriptionUserEmail(email)
                .stream()
                .map(p -> new PaymentResponse(
                        p.getSubscription().getPlan().getName(),
                        p.getAmount(),
                        p.getPaymentDate(),
                        p.getTransactionId(),
                        p.getStatus()
                )).collect(Collectors.toList());
    }
}
