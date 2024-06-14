package com.miw.tripplanner.services;

import com.miw.tripplanner.dtos.PlanDto;
import java.util.List;

public interface PlanService {
    List<PlanDto> getAllPlanes();
    PlanDto getPlan(Integer id);
    Integer createPlan(PlanDto planDto);
    void updatePlan(Integer id, PlanDto planDto);
    void deletePlan(Integer id);
}
