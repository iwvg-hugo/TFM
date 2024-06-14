package com.miw.tripplanner.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PlanDto {
    private Integer id;
    private Integer idViaje;
    private Integer idUbicacion;
    private Integer idHorario;
    private Integer idPago;
    private String nombre;
    private Integer importancia;
    private String descripcion;

    public PlanDto() {
        this.id = 0;
        this.idViaje = 0;
        this.idUbicacion = 0;
        this.idHorario = 0;
        this.idPago = 0;
    }
}