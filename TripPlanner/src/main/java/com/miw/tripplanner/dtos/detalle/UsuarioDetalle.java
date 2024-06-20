package com.miw.tripplanner.dtos.detalle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDetalle {
    private Integer id;
    private String fullName;
    private String email;
}
