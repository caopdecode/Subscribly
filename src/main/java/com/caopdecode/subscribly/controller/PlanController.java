package com.caopdecode.subscribly.controller;

import com.caopdecode.subscribly.dto.PlanDTO;
import com.caopdecode.subscribly.dto.PlanResponse;
import com.caopdecode.subscribly.service.PlanService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plans")
public class PlanController {
    private final PlanService planService;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @PostMapping
    public ResponseEntity<String> createPlan (@RequestBody @Valid PlanDTO planDTO){
        try{
            planService.createPlan(planDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Plan created successfully");
        } catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<PlanResponse>> getAllPlans(){
        return ResponseEntity.ok(planService.getAllPlans());
    }
}
