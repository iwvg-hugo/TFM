package com.miw.tripplanner.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UsuarioPagoDto {
    private Integer idUsuario;
    private Integer idPago;
    private Boolean pagador;
}