package com.miw.tripplanner.services.implementations;

import com.miw.tripplanner.dtos.PlanDto;
import com.miw.tripplanner.dtos.detalle.PlanDetalleDto;
import com.miw.tripplanner.mappers.*;
import com.miw.tripplanner.services.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
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

    @Override
    public List<PlanDto> getAllPlanes() {
        return this.planMapper.getAllPlanes();
    }

    @Override
    public PlanDetalleDto getPlan(Integer id) {
        PlanDto plan = this.planMapper.getPlan(id);
        PlanDetalleDto planDetalle = new PlanDetalleDto();
        planDetalle.setId(plan.getId());
        planDetalle.setIdViaje(plan.getIdViaje());
        planDetalle.setUbicacion(this.ubicacionMapper.getUbicacion(plan.getIdUbicacion()));
        planDetalle.setHorario(this.horarioMapper.getHorario(plan.getIdHorario()));
        planDetalle.setNombre(plan.getNombre());
        planDetalle.setImportancia(plan.getImportancia());
        planDetalle.setDescripcion(plan.getDescripcion());
        return planDetalle;
    }

    @Override
    public Integer createPlan(PlanDto planDto) {
        planDto.setIdUbicacion(this.ubicacionMapper.createUbicacion(planDto.getUbicacion()));
        planDto.setIdHorario(this.horarioMapper.createHorario(planDto.getHorario()));
        return planMapper.createPlan(planDto);
    }

    @Override
    public void updatePlan(Integer id, PlanDto planDto) {
        this.ubicacionMapper.deleteUbicacion(planDto.getIdUbicacion());
        planDto.setIdUbicacion(this.ubicacionMapper.createUbicacion(planDto.getUbicacion()));

        this.horarioMapper.deleteHorario(planDto.getIdHorario());
        planDto.setIdHorario(this.horarioMapper.createHorario(planDto.getHorario()));
        this.planMapper.updatePlan(id, planDto);
    }

    @Override
    public void deletePlan(Integer id) {
        PlanDto plan = planMapper.getPlan(id);
        this.ticketMapper.deleteTicketsByIdPlan(id);
        this.planMapper.deletePlan(id);
        this.horarioMapper.deleteHorario(plan.getIdHorario());
        this.ubicacionMapper.deleteUbicacion(plan.getIdUbicacion());
    }

    @Override
    public void deletePlanesByIdViaje(Integer idViaje) {
        List<PlanDto> planes = this.planMapper.getPlanesByIdViaje(idViaje);
        for (PlanDto plan : planes) {
            deletePlan(plan.getId());
        }
    }

    @Override
    public List<PlanDetalleDto> findPlanesByIdViaje(@RequestParam(value = "idViaje", required = true) Integer idViaje) {
        List<PlanDto> planes = this.planMapper.getPlanesByIdViaje(idViaje);
        List<PlanDetalleDto> planesDetalle = new ArrayList<>();
        for (PlanDto plan : planes) {
            planesDetalle.add(this.getPlan(plan.getId()));
        }
        return planesDetalle;
    }
}
