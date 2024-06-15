package com.miw.tripplanner.services;

import com.miw.tripplanner.dtos.PagoDto;
import com.miw.tripplanner.dtos.detalle.PagoDetalleDto;

import java.util.List;

public interface PagoService {
    List<PagoDto> getAllPagos();
    PagoDetalleDto getPago(Integer id);
    Integer createPago(Integer idUsuario, PagoDto pagoDto, Boolean pagador);
    void updatePago(Integer id, PagoDto pagoDto);
    void deletePago(Integer id);
}
