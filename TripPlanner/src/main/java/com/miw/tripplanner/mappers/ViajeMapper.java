package com.miw.tripplanner.mappers;

import com.miw.tripplanner.dtos.ViajeDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViajeMapper {

    List<ViajeDto> getAllViajes();
    ViajeDto getViajeById(Integer id);
    List<ViajeDto> findViajesByUserId(Integer id);
    Integer createViaje(ViajeDto viajeDto);
    void updateViaje(ViajeDto viajeDto);
    void deleteViaje(Integer id);

}
