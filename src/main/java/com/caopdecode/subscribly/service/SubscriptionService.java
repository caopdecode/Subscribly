package com.caopdecode.subscribly.service;

import com.caopdecode.subscribly.dto.SubscriptionDTO;
import com.caopdecode.subscribly.dto.SubscriptionResponse;

import java.util.List;

public interface SubscriptionService {
    void subscribe(SubscriptionDTO subscriptionDTO);
    List<SubscriptionResponse> getSubscriptionsByUser(String email);
    void renewSubscription(Long id);
    void cancelSubscription(Long id);
}
