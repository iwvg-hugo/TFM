package com.miw.tripplanner.services;

import com.miw.tripplanner.dtos.ViajeDto;
import com.miw.tripplanner.dtos.detalle.ViajeDetalleDto;
import com.miw.tripplanner.dtos.requests.ViajeRequest;

import java.util.List;

public interface ViajeService {
    List<ViajeDto> getAllViajes();
    ViajeDetalleDto getViaje(Integer id);
    List<ViajeDto> findViajesByUserId(Integer id);
    Integer createViaje(ViajeRequest viajeRequest);
    void updateViaje(Integer id,  ViajeRequest viajeDto);
    void deleteViaje(Integer id);
}
