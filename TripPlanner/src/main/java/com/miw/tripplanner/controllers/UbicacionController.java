package com.miw.tripplanner.controllers;

import com.miw.tripplanner.dtos.UbicacionDto;
import com.miw.tripplanner.services.UbicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ubicaciones")
public class UbicacionController {

        @Autowired
        UbicacionService ubicacionService;

        @GetMapping()
        public List<UbicacionDto> getUbicaciones() {
            return ubicacionService.getAllUbicaciones();
        }

        @GetMapping("/{id}")
        public UbicacionDto getUbicacion(@PathVariable Integer id) {
            return ubicacionService.getUbicacion(id);
        }

        @PostMapping()
        public Integer createUbicacion(@RequestBody UbicacionDto ubicacionDto) {
            return ubicacionService.createUbicacion(ubicacionDto);
        }

        @PutMapping("/{id}")
        public void updateUbicacion(@PathVariable Integer id, @RequestBody UbicacionDto ubicacionDto) {
            ubicacionService.updateUbicacion(id, ubicacionDto);
        }

        @DeleteMapping("/{id}")
        public void deleteUbicacion(@PathVariable Integer id) {
            ubicacionService.deleteUbicacion(id);
        }
}