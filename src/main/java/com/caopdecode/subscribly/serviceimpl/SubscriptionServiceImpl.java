package com.caopdecode.subscribly.serviceimpl;

import com.caopdecode.subscribly.dto.SubscriptionDTO;
import com.caopdecode.subscribly.dto.SubscriptionResponse;
import com.caopdecode.subscribly.model.Plan;
import com.caopdecode.subscribly.model.Subscription;
import com.caopdecode.subscribly.model.SubscriptionStatus;
import com.caopdecode.subscribly.model.User;
import com.caopdecode.subscribly.notification.NotificationService;
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
    private final NotificationService notificationService;

    public SubscriptionServiceImpl(UserRepository userRepository, PlanRepository planRepository, SubscriptionRepository subscriptionRepository, NotificationService notificationService) {
        this.userRepository = userRepository;
        this.planRepository = planRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.notificationService = notificationService;
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

        subscription.setStatus(SubscriptionStatus.ACTIVE);

        subscriptionRepository.save(subscription);
        notificationService.sendNotification(
                user.getEmail(),
                "Thanks for subscribe to the plan " + plan.getName() + "!\n" +
                        "Your subscription is active until " + end.toString()
        );
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

    @Override
    public void renewSubscription(Long id) {
        Subscription sub = subscriptionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Subscription not found"));

        if(sub.getStatus() != SubscriptionStatus.ACTIVE){
            throw new IllegalStateException("Only active subscriptions can be renewed");
        }

        sub.setEndDate(sub.getEndDate().plusDays(sub.getPlan().getDurationDays()));
        subscriptionRepository.save(sub);
        notificationService.sendNotification(
                sub.getUser().getEmail(),
                "Thanks for renew your subscription to the plan " + sub.getPlan().getName() + "!\n" +
                        "Your subscription is active until " + sub.getEndDate().toString()
        );
    }

    @Override
    public void cancelSubscription(Long id){
        Subscription sub = subscriptionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Subscription not found"));

        if(sub.getStatus() == SubscriptionStatus.CANCELLED){
            throw new IllegalStateException("Subscription is already cancelled");
        }

        sub.setStatus(SubscriptionStatus.CANCELLED);
        subscriptionRepository.save(sub);
        notificationService.sendNotification(
                sub.getUser().getEmail(),
                "Your subscription is cancelled, we hope see you again " + sub.getPlan().getName()
        );
    }
}
