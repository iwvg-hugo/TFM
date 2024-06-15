package com.miw.tripplanner.services;

import com.miw.tripplanner.dtos.PlanDto;
import com.miw.tripplanner.dtos.detalle.PlanDetalleDto;

import java.util.List;

public interface PlanService {
    List<PlanDto> getAllPlanes();
    PlanDetalleDto getPlan(Integer id);
    Integer createPlan(PlanDto planDto);
    void updatePlan(Integer id, PlanDto planDto);
    void deletePlan(Integer id);
    List<PlanDetalleDto> getPlanesByIdViaje(Integer idViaje);
    void deletePlanesByIdViaje(Integer idViaje);
}
