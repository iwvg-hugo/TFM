package com.miw.tripplanner.services;

import com.miw.tripplanner.dtos.PagoDto;

import java.util.List;

public interface PagoService {
    List<PagoDto> getAllPagos();
    PagoDto getPago(Integer id);
    Integer createPago(Integer idUsuario, PagoDto pagoDto, Boolean pagador);
    void updatePago(Integer id, PagoDto pagoDto);
    void deletePago(Integer id);
}
