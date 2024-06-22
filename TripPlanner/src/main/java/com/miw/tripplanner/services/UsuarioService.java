package com.miw.tripplanner.services;

import com.miw.tripplanner.dtos.*;
import com.miw.tripplanner.dtos.detalle.UsuarioDetalle;

import java.util.List;

public interface UsuarioService {
    List<UsuarioDto> getAllUsuarios();
    UsuarioDto getUsuario(Integer id);
    Integer createUsuario(UsuarioDto usuarioDto);
    void updateUsuario(Integer id, UsuarioDto usuarioDto);
    void deleteUsuario(Integer id);
    List<UsuarioDetalle> findUsuariosByIdViaje(Integer idViaje);

    UsuarioDto findUsuarioByEmail(String email);
}