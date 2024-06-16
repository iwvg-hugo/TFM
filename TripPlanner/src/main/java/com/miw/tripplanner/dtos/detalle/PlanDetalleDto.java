package com.miw.tripplanner.dtos.detalle;

import com.miw.tripplanner.dtos.HorarioDto;
import com.miw.tripplanner.dtos.UbicacionDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PlanDetalleDto {
    private Integer id;
    private Integer idViaje;
    private UbicacionDto ubicacion;
    private HorarioDto horario;
    private Integer idPago;
    private String nombre;
    private Integer importancia;
    private String descripcion;
}