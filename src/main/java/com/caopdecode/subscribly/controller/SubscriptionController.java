package com.caopdecode.subscribly.controller;

import com.caopdecode.subscribly.dto.SubscriptionDTO;
import com.caopdecode.subscribly.dto.SubscriptionResponse;
import com.caopdecode.subscribly.service.SubscriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping
    public ResponseEntity<String> subscribe (@RequestBody SubscriptionDTO subscriptionDTO){
        try{
            subscriptionService.subscribe(subscriptionDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Subscription created successfully");
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{email}")
    public ResponseEntity<List<SubscriptionResponse>> getByUser(@PathVariable String email){
        return ResponseEntity.ok(subscriptionService.getSubscriptionsByUser(email));
    }
}
