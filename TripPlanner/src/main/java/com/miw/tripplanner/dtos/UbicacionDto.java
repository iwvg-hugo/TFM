package com.miw.tripplanner.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class UbicacionDto {
    private Integer id;
    private Boolean esExterior;
    private String tipoVestimenta;
    private List<String> requisitos;
    private String direccion;
    private String coordenadas;

    public UbicacionDto() {
        this.id = 0;
        this.esExterior = false;
    }
}