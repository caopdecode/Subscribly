package com.caopdecode.subscribly.service;

import com.caopdecode.subscribly.dto.PlanDTO;
import com.caopdecode.subscribly.dto.PlanResponse;

import java.util.List;

public interface PlanService {
    void createPlan(PlanDTO planDTO);
    List<PlanResponse> getAllPlans();
}
