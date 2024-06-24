package com.miw.tripplanner.controllers;

import com.miw.tripplanner.dtos.PagoDto;
import com.miw.tripplanner.dtos.detalle.PagoDetalleDto;
import com.miw.tripplanner.dtos.requests.PagoRequest;
import com.miw.tripplanner.services.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    @Autowired
    PagoService pagoService;

    @GetMapping()
    public List<PagoDetalleDto> getPagos() {
        return pagoService.getAllPagos();
    }

    @GetMapping("/usuario/{id}")
    public List<PagoDetalleDto> getPagosByIdViaje(@PathVariable Integer id) {
        return pagoService.getPagosByIdViaje(id);
    }

    @GetMapping("/{id}")
    public PagoDetalleDto getPago(@PathVariable Integer id) {
        return pagoService.getPago(id);
    }

    @PostMapping()
    public Integer createPago(@RequestBody PagoRequest pagoRequest) {
        return pagoService.createPago(pagoRequest);
    }

    @PutMapping("/{id}")
    public void updatePago(@PathVariable Integer id, @RequestBody PagoDto pagoDto) {
        pagoService.updatePago(id, pagoDto);
    }

    @DeleteMapping("/{id}")
    public void deletePago(@PathVariable Integer id) {
        pagoService.deletePago(id);
    }
}

