package com.miw.tripplanner.dtos.detalle;

import com.miw.tripplanner.dtos.HorarioDto;
import com.miw.tripplanner.dtos.PropuestaDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ViajeDetalleDto {
    private Integer id;
    private HorarioDto horario;
    private List<PropuestaDto> propuestas;
    private List<PlanDetalleDto> planes;
}
