package com.miw.tripplanner.controllers;

import com.miw.tripplanner.dtos.ViajeDto;
import com.miw.tripplanner.dtos.detalle.ViajeDetalleDto;
import com.miw.tripplanner.dtos.requests.ViajeRequest;
import com.miw.tripplanner.services.ViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/viajes")
public class ViajeController {

    @Autowired
    ViajeService viajeService;

    @GetMapping()
    public List<ViajeDto> getViajes() {
        return viajeService.getAllViajes();
    }

    @GetMapping("/{id}")
    public ViajeDetalleDto getViaje(@PathVariable Integer id) {
        return viajeService.getViaje(id);
    }

    @GetMapping("/usuario/{idUsuario}")
    public List<ViajeDto> findViajesByUserId(@PathVariable Integer idUsuario) {
        return viajeService.findViajesByUserId(idUsuario);
    }

    @PostMapping()
    public Integer createViaje(@RequestBody ViajeRequest viajeRequest) {
        return viajeService.createViaje(viajeRequest);
    }

    @PutMapping("/{id}")
    public void updateViaje(@PathVariable Integer id, @RequestBody  ViajeRequest viajeDto) {
        viajeService.updateViaje(id, viajeDto);
    }

    @DeleteMapping("/{id}")
    public void deleteViaje(@PathVariable Integer id) {
        viajeService.deleteViaje(id);
    }
}
