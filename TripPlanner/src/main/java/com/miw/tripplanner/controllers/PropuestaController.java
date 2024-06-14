package com.miw.tripplanner.controllers;

import com.miw.tripplanner.dtos.PropuestaDto;
import com.miw.tripplanner.services.PropuestaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/propuestas")
public class PropuestaController {

    @Autowired
    PropuestaService propuestaService;

    @GetMapping()
    public List<PropuestaDto> getPropuestas() {
        return propuestaService.getAllPropuestas();
    }

    @GetMapping("/{id}")
    public PropuestaDto getPropuesta(@PathVariable Integer id) {
        return propuestaService.getPropuesta(id);
    }

    @PostMapping()
    public Integer createPropuesta(@RequestBody PropuestaDto propuestaDto) {
        return propuestaService.createPropuesta(propuestaDto);
    }

    @PutMapping("/{id}")
    public void updatePropuesta(@PathVariable Integer id, @RequestBody PropuestaDto propuestaDto) {
        propuestaService.updatePropuesta(id, propuestaDto);
    }

    @DeleteMapping("/{id}")
    public void deletePropuesta(@PathVariable Integer id) {
        propuestaService.deletePropuesta(id);
    }

}
