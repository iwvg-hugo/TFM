package com.miw.tripplanner.dtos.detalle;

import com.miw.tripplanner.dtos.HorarioDto;
import com.miw.tripplanner.dtos.UsuarioDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class PagoDetalleDto {
    private Integer id;
    private Float total;
    private String descripcion;
    private Integer idPagador;
    private List<UsuarioDto> usuarios;
    private HorarioDto horario;

    public PagoDetalleDto() {
        this.usuarios = new ArrayList<>();
    }
}