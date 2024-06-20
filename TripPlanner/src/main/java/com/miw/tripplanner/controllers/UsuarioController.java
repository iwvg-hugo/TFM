package com.miw.tripplanner.controllers;

import com.miw.tripplanner.dtos.*;
import com.miw.tripplanner.dtos.detalle.UsuarioDetalle;
import com.miw.tripplanner.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping()
    public List<UsuarioDto> getUsuarios() {
        return usuarioService.getAllUsuarios();
    }

    @GetMapping("/{id}")
    public UsuarioDto getUsuario(@PathVariable Integer id) {
        return usuarioService.getUsuario(id);
    }

    @PostMapping()
    public Integer createUsuario(@RequestBody UsuarioDto usuarioDto) {
        return usuarioService.createUsuario(usuarioDto);
    }

    @PutMapping("/{id}")
    public void updateUsuario(@PathVariable Integer id, @RequestBody UsuarioDto usuarioDto) {
        usuarioService.updateUsuario(id, usuarioDto);
    }

    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable Integer id) {
        usuarioService.deleteUsuario(id);
    }

    @GetMapping("/search")
    public List<UsuarioDetalle> findUsuarios(@RequestParam(value = "idViaje", required = true) Integer idViaje) {
        return usuarioService.findUsuariosByIdViaje(idViaje);
    }
}