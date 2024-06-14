package com.miw.tripplanner.services;

import com.miw.tripplanner.dtos.ViajeDto;
import java.util.List;

public interface ViajeService {
    List<ViajeDto> getAllViajes();
    ViajeDto getViaje(Integer id);
    List<ViajeDto> findViajesByUserId(Integer id);
    Integer createViaje(Integer idUsuario, ViajeDto viajeDto);
    void updateViaje(Integer id, ViajeDto viajeDto);
    void deleteViaje(Integer id);
}
