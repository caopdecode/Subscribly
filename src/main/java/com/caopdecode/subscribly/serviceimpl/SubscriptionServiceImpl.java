package com.caopdecode.subscribly.serviceimpl;

import com.caopdecode.subscribly.dto.SubscriptionDTO;
import com.caopdecode.subscribly.dto.SubscriptionResponse;
import com.caopdecode.subscribly.model.Plan;
import com.caopdecode.subscribly.model.Subscription;
import com.caopdecode.subscribly.model.User;
import com.caopdecode.subscribly.repository.PlanRepository;
import com.caopdecode.subscribly.repository.SubscriptionRepository;
import com.caopdecode.subscribly.repository.UserRepository;
import com.caopdecode.subscribly.service.SubscriptionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    private final UserRepository userRepository;
    private final PlanRepository planRepository;
    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionServiceImpl(UserRepository userRepository, PlanRepository planRepository, SubscriptionRepository subscriptionRepository) {
        this.userRepository = userRepository;
        this.planRepository = planRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    public void subscribe(SubscriptionDTO subscriptionDTO){
        User user = userRepository.findById(subscriptionDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Plan plan = planRepository.findById(subscriptionDTO.getPlanId())
                .orElseThrow(() -> new IllegalArgumentException("Plan not found"));

        Subscription subscription = new Subscription();
        subscription.setUser(user);
        subscription.setPlan(plan);

        LocalDate start = LocalDate.now();
        LocalDate end = start.plusDays(plan.getDurationDays());

        subscription.setStartDate(start);
        subscription.setEndDate(end);

        subscriptionRepository.save(subscription);
    }

    @Override
    public List<SubscriptionResponse> getSubscriptionsByUser(String email){
        return subscriptionRepository.findByUserEmail(email)
                .stream()
                .map(s -> new SubscriptionResponse(
                        s.getUser().getEmail(),
                        s.getPlan().getName(),
                        s.getStartDate(),
                        s.getEndDate()
                )).collect(Collectors.toList());
    }
}
