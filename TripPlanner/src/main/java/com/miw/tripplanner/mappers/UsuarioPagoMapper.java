package com.miw.tripplanner.mappers;

import com.miw.tripplanner.dtos.UsuarioPagoDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UsuarioPagoMapper {
    List<UsuarioPagoDto> getAllUsuariosPagos();
    UsuarioPagoDto getUsuarioPago(Integer id);
    Integer createUsuarioPago(UsuarioPagoDto usuarioPagoDto);
    void updateUsuarioPago(Integer id, UsuarioPagoDto usuarioPagoDto);
    void deleteUsuarioPago(Integer id);
}
