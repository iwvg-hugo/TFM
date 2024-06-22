package com.miw.tripplanner.dtos.requests;

import com.miw.tripplanner.dtos.PropuestaDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViajeRequest {
    private String titulo;
    private String userEmail;
    private Timestamp fechaInicio;
    private Timestamp fechaFin;
    private List<String> emailParticipantes;
    private List<PropuestaDto> propuestas;
    private String imagen;
}
