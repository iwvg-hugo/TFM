package com.miw.tripplanner.controllers;

import com.miw.tripplanner.dtos.PlanDto;
import com.miw.tripplanner.dtos.detalle.PlanDetalleDto;
import com.miw.tripplanner.services.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/planes")
public class PlanController {

    @Autowired
    PlanService planService;

    @GetMapping()
    public List<PlanDto> getPlanes() {
        return planService.getAllPlanes();
    }

    @GetMapping("/{id}")
    public PlanDetalleDto getPlan(@PathVariable Integer id) {
        return planService.getPlan(id);
    }

    @PostMapping()
    public Integer createPlan(@RequestBody PlanDto planDto) {
        return planService.createPlan(planDto);
    }

    @PutMapping("/{id}")
    public void updatePlan(@PathVariable Integer id, @RequestBody PlanDto planDto) {
        planService.updatePlan(id, planDto);
    }

    @DeleteMapping("/{id}")
    public void deletePlan(@PathVariable Integer id) {
        planService.deletePlan(id);
    }

    @GetMapping("/search")
    public List<PlanDetalleDto> findPlanesByIdViaje(@RequestParam(value = "idViaje", required = true) Integer idViaje) {
        return planService.findPlanesByIdViaje(idViaje);
    }
}