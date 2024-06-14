package com.miw.tripplanner.services;

import com.miw.tripplanner.dtos.UbicacionDto;
import java.util.List;

public interface UbicacionService {
    List<UbicacionDto> getAllUbicaciones();
    UbicacionDto getUbicacion(Integer id);
    Integer createUbicacion(UbicacionDto ubicacionDto);
    void updateUbicacion(Integer id, UbicacionDto ubicacionDto);
    void deleteUbicacion(Integer id);
}
