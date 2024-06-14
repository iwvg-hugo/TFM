package com.miw.tripplanner.mappers;

import com.miw.tripplanner.dtos.UsuarioViajeDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UsuarioViajeMapper {
    List<UsuarioViajeDto> getAllUsuariosViajes();
    UsuarioViajeDto getUsuarioViaje(Integer id);
    Integer createUsuarioViaje(UsuarioViajeDto usuarioViajeDto);
    void updateUsuarioViaje(Integer id, UsuarioViajeDto usuarioViajeDto);
    void deleteUsuarioViaje(Integer id);
}