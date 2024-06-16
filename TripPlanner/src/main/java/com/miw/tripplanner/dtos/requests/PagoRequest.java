package com.miw.tripplanner.dtos.requests;

import com.miw.tripplanner.dtos.PagoDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PagoRequest {
    private Integer idUsuario;
    private Boolean pagador;
    private PagoDto pagoDto;
}
