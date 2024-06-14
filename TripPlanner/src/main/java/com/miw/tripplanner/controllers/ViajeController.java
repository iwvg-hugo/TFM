package com.miw.tripplanner.controllers;

import com.miw.tripplanner.dtos.ViajeDto;
import com.miw.tripplanner.services.ViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/viajes")
public class ViajeController {

    @Autowired
    ViajeService viajeService;

    @GetMapping()
    public List<ViajeDto> getViajes() {
        return viajeService.getAllViajes();
    }

    @GetMapping("/{id}")
    public ViajeDto getViaje(@PathVariable Integer id) {
        return viajeService.getViaje(id);
    }

    @GetMapping("/usuario/{idUsuario}")
    public List<ViajeDto> findViajesByUserId(@PathVariable Integer idUsuario) {
        return viajeService.findViajesByUserId(idUsuario);
    }

    @PostMapping()
    public Integer createViaje(@RequestBody Integer idUsuario) {
        return viajeService.createViaje(idUsuario, new ViajeDto());
    }

    @PutMapping("/{id}")
    public void updateViaje(@PathVariable Integer id, @RequestBody ViajeDto viajeDto) {
        viajeService.updateViaje(id, viajeDto);
    }

    @DeleteMapping("/{id}")
    public void deleteViaje(@PathVariable Integer id) {
        viajeService.deleteViaje(id);
    }
}
