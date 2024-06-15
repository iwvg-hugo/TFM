package com.miw.tripplanner.services.implementations;

import com.miw.tripplanner.dtos.HorarioDto;
import com.miw.tripplanner.dtos.PlanDto;
import com.miw.tripplanner.dtos.UbicacionDto;
import com.miw.tripplanner.mappers.*;
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

    @Autowired
    private TicketMapper ticketMapper;

    @Autowired
    private PagoServiceImpl pagoServiceImpl;

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
        PlanDto plan = planMapper.getPlan(id);
        ticketMapper.deleteTicketsByIdPlan(id);
        pagoServiceImpl.deletePago(id);
        planMapper.deletePlan(id);
        horarioMapper.deleteHorario(plan.getIdHorario());
        ubicacionMapper.deleteUbicacion(plan.getIdUbicacion());
    }

    @Override
    public void deletePlanesByIdViaje(Integer idViaje) {
        List<PlanDto> planes = planMapper.getPlanesByIdViaje(idViaje);
        for (PlanDto plan : planes) {
            deletePlan(plan.getId());
        }
    }

    @Override
    public List<PlanDto> getPlanesByIdViaje(Integer idViaje) {
        return planMapper.getPlanesByIdViaje(idViaje);
    }
}
