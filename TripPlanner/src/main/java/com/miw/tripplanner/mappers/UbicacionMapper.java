package com.miw.tripplanner.mappers;

import com.miw.tripplanner.dtos.UbicacionDto;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface UbicacionMapper {

    List<UbicacionDto> getAllUbicaciones();
    UbicacionDto getUbicacion(Integer id);
    Integer createUbicacion(UbicacionDto ubicacionDto);
    void updateUbicacion(Integer id, UbicacionDto ubicacionDto);
    void deleteUbicacion(Integer id);

}
