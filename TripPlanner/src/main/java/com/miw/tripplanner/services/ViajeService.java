package com.miw.tripplanner.services;

import com.miw.tripplanner.dtos.ViajeDto;
import com.miw.tripplanner.dtos.detalle.ViajeDetalleDto;

import java.util.List;

public interface ViajeService {
    List<ViajeDto> getAllViajes();
    ViajeDetalleDto getViaje(Integer id);
    List<ViajeDto> findViajesByUserId(Integer id);
    Integer createViaje(Integer idUsuario, ViajeDto viajeDto);
    void updateViaje(Integer id, ViajeDto viajeDto);
    void deleteViaje(Integer id);
}
