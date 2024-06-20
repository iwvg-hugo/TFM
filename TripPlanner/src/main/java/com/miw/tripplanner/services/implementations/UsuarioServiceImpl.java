package com.miw.tripplanner.services.implementations;

import com.miw.tripplanner.dtos.UsuarioDto;
import com.miw.tripplanner.dtos.detalle.UsuarioDetalle;
import com.miw.tripplanner.mappers.UsuarioMapper;
import com.miw.tripplanner.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Override
    public List<UsuarioDto> getAllUsuarios() {
        return usuarioMapper.getAllUsuarios();
    }

    @Override
    public UsuarioDto getUsuario(Integer id) {
        return usuarioMapper.getUsuario(id);
    }

    @Override
    public Integer createUsuario(UsuarioDto usuarioDto) {
        return usuarioMapper.createUsuario(usuarioDto);
    }

    @Override
    public void updateUsuario(Integer id, UsuarioDto usuarioDto) {
        usuarioMapper.updateUsuario(id, usuarioDto);
    }

    @Override
    public void deleteUsuario(Integer id) {
        usuarioMapper.deleteUsuario(id);
    }

    @Override
    public List<UsuarioDetalle> findUsuariosByIdViaje(Integer idViaje) {
        return usuarioMapper.findUsuariosByIdViaje(idViaje);
    }
}
