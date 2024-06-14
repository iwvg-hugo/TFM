package com.miw.tripplanner.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PropuestaDto {
    private Integer id;
    private Integer idViaje;
    private String nombre;
    private Float valoracion;
    private String descripcion;
    private Float presupuesto;
    private Boolean ganadora;
}
