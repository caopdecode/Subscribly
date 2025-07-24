package com.caopdecode.subscribly.serviceimpl;

import com.caopdecode.subscribly.dto.PlanDTO;
import com.caopdecode.subscribly.dto.PlanResponse;
import com.caopdecode.subscribly.model.Plan;
import com.caopdecode.subscribly.repository.PlanRepository;
import com.caopdecode.subscribly.service.PlanService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlanServiceImpl implements PlanService {
    private final PlanRepository planRepository;

    public PlanServiceImpl(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    @Override
    public void createPlan(PlanDTO planDTO){
        if(planRepository.findByName(planDTO.getName()).isPresent()){
            throw new IllegalArgumentException("Plan already exists");
        }

        Plan plan = new Plan();
        plan.setName(planDTO.getName());
        plan.setPrice(planDTO.getPrice());
        plan.setDurationDays(planDTO.getDurationDays());
        plan.setDescription(planDTO.getDescription());

        planRepository.save(plan);
    }

    @Override
    public List<PlanResponse> getAllPlans(){
        return planRepository.findAll()
                .stream()
                .map(plan -> new PlanResponse(
                        plan.getName(),
                        plan.getPrice(),
                        plan.getDurationDays(),
                        plan.getDescription()
                )).collect(Collectors.toList());
    }
}
