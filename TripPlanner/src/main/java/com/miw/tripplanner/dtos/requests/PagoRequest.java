package com.miw.tripplanner.dtos.requests;

import com.miw.tripplanner.dtos.PagoDto;
import com.miw.tripplanner.dtos.UsuarioDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PagoRequest {
    private Integer idUsuario;
    private Boolean pagador;
    private PagoDto pagoDto;
    private List<UsuarioDto> usuariosImplicados; // Agregar esta línea
}
