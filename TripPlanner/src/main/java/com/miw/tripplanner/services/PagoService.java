package com.miw.tripplanner.services;

import com.miw.tripplanner.dtos.PagoDto;
import com.miw.tripplanner.dtos.detalle.PagoDetalleDto;
import com.miw.tripplanner.dtos.requests.PagoRequest;

import java.util.List;

public interface PagoService {
    List<PagoDetalleDto> getAllPagos();
    List<PagoDetalleDto> getPagosByIdViaje(Integer id);
    PagoDetalleDto getPago(Integer id);
    Integer createPago(PagoRequest pagoRequest);
    void updatePago(Integer id, PagoDto pagoDto);
    void deletePago(Integer id);
}
