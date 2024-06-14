package com.miw.tripplanner.services.implementations;

import com.miw.tripplanner.dtos.UbicacionDto;
import com.miw.tripplanner.mappers.UbicacionMapper;
import com.miw.tripplanner.services.UbicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UbicacionServiceImpl implements UbicacionService {

    @Autowired
    private UbicacionMapper ubicacionMapper;

    public List<UbicacionDto> getAllUbicaciones() {
        return ubicacionMapper.getAllUbicaciones();
    }

    public UbicacionDto getUbicacion(Integer id) {
        return ubicacionMapper.getUbicacion(id);
    }

    public Integer createUbicacion(UbicacionDto ubicacionDto) {
        return ubicacionMapper.createUbicacion(ubicacionDto);
    }

    public void updateUbicacion(Integer id, UbicacionDto ubicacionDto) {
        ubicacionMapper.updateUbicacion(id, ubicacionDto);
    }

    public void deleteUbicacion(Integer id) {
        ubicacionMapper.deleteUbicacion(id);
    }
}
