package com.miw.tripplanner.controllers;

import com.miw.tripplanner.dtos.HorarioDto;
import com.miw.tripplanner.services.HorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/horarios")
public class HorarioController {

    @Autowired
    HorarioService horarioService;

    @GetMapping()
    public List<HorarioDto> getHorarios() {
        return horarioService.getAllHorarios();
    }

    @GetMapping("/{id}")
    public HorarioDto getHorario(@PathVariable Integer id) {
        return horarioService.getHorario(id);
    }

    @PostMapping()
    public Integer createHorario(@RequestBody HorarioDto horarioDto) {
        return horarioService.createHorario(horarioDto);
    }

    @PutMapping("/{id}")
    public void updateHorario(@PathVariable Integer id, @RequestBody HorarioDto horarioDto) {
        horarioService.updateHorario(id, horarioDto);
    }

    @DeleteMapping("/{id}")
    public void deleteHorario(@PathVariable Integer id) {
        horarioService.deleteHorario(id);
    }
}

