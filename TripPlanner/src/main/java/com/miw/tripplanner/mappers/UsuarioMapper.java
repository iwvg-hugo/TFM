package com.miw.tripplanner.mappers;

import com.miw.tripplanner.dtos.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UsuarioMapper {
    
    List<UsuarioDto> getAllUsuarios();
    UsuarioDto getUsuario(Integer id);
    Integer createUsuario(UsuarioDto usuarioDto);
    void updateUsuario(Integer id, UsuarioDto usuarioDto);
    void deleteUsuario(Integer id);
    UsuarioDto findUsuarioByEmail(String email);

}
