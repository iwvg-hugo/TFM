package com.miw.tripplanner.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UsuarioDto {
    private Integer id;
    private String name;
    private String email;
    private String password;
}
