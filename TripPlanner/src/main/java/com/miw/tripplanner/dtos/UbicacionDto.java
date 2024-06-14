package com.miw.tripplanner.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UbicacionDto {
    private Integer id;
    private Boolean esExterior;
    private String tipoVestimenta;
    private String requisitos;
    private String direccion;
    private String coordenadas;

    public UbicacionDto() {
        this.id = 0;
        this.esExterior = false;
    }
}