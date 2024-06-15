package com.miw.tripplanner.mappers;

import com.miw.tripplanner.dtos.PlanDto;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface PlanMapper {

    List<PlanDto> getAllPlanes();
    PlanDto getPlan(Integer id);
    Integer createPlan(PlanDto planDto);
    void updatePlan(Integer id, PlanDto planDto);
    void deletePlan(Integer id);
    List<PlanDto> getPlanesByIdViaje(Integer idViaje);

}
