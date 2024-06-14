package com.miw.tripplanner.services.implementations;

import com.miw.tripplanner.dtos.HorarioDto;
import com.miw.tripplanner.dtos.PlanDto;
import com.miw.tripplanner.dtos.UbicacionDto;
import com.miw.tripplanner.mappers.HorarioMapper;
import com.miw.tripplanner.mappers.PlanMapper;
import com.miw.tripplanner.mappers.UbicacionMapper;
import com.miw.tripplanner.services.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {

    @Autowired
    private PlanMapper planMapper;

    @Autowired
    private UbicacionMapper ubicacionMapper;

    @Autowired
    private HorarioMapper horarioMapper;

    @Override
    public List<PlanDto> getAllPlanes() {
        return planMapper.getAllPlanes();
    }

    @Override
    public PlanDto getPlan(Integer id) {
        return planMapper.getPlan(id);
    }

    @Override
    public Integer createPlan(PlanDto planDto) {
        planDto.setIdUbicacion(ubicacionMapper.createUbicacion(new UbicacionDto()));
        planDto.setIdHorario(horarioMapper.createHorario(new HorarioDto()));
        return planMapper.createPlan(planDto);
    }

    @Override
    public void updatePlan(Integer id, PlanDto planDto) {
        planMapper.updatePlan(id, planDto);
    }

    @Override
    public void deletePlan(Integer id) {
        planMapper.deletePlan(id);
    }
}
