package com.miw.tripplanner.services.implementations;

import com.miw.tripplanner.dtos.HorarioDto;
import com.miw.tripplanner.dtos.PlanDto;
import com.miw.tripplanner.dtos.UbicacionDto;
import com.miw.tripplanner.dtos.detalle.PlanDetalleDto;
import com.miw.tripplanner.mappers.*;
import com.miw.tripplanner.services.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    private PagoServiceImpl pagoServiceImpl;

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
        planDetalle.setIdPago(plan.getIdPago());
        planDetalle.setNombre(plan.getNombre());
        planDetalle.setImportancia(plan.getImportancia());
        planDetalle.setDescripcion(plan.getDescripcion());
        return planDetalle;
    }

    @Override
    public Integer createPlan(PlanDto planDto) {
        planDto.setIdUbicacion(this.ubicacionMapper.createUbicacion(new UbicacionDto()));
        planDto.setIdHorario(this.horarioMapper.createHorario(new HorarioDto()));
        return planMapper.createPlan(planDto);
    }

    @Override
    public void updatePlan(Integer id, PlanDto planDto) {
        this.planMapper.updatePlan(id, planDto);
    }

    @Override
    public void deletePlan(Integer id) {
        PlanDto plan = planMapper.getPlan(id);
        this.ticketMapper.deleteTicketsByIdPlan(id);
        this.pagoServiceImpl.deletePago(id);
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
    public List<PlanDetalleDto> getPlanesByIdViaje(Integer idViaje) {
        List<PlanDto> planes = this.planMapper.getPlanesByIdViaje(idViaje);
        List<PlanDetalleDto> planesDetalle = new ArrayList<>();
        for (PlanDto plan : planes) {
            planesDetalle.add(this.getPlan(plan.getId()));
        }
        return planesDetalle;
    }
}
