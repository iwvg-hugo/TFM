package com.miw.tripplanner.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ViajeDto {
    private Integer id;
    private Integer idHorario;

    public ViajeDto() {
        this.id = 0;
    }
}
