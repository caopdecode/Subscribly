package com.caopdecode.subscribly.repository;

import com.caopdecode.subscribly.model.Payment;
import com.caopdecode.subscribly.model.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findBySubscriptionUserEmail(String email);
    boolean existsBySubscriptionIdAndStatus(Long subscriptionId, PaymentStatus status);
}
