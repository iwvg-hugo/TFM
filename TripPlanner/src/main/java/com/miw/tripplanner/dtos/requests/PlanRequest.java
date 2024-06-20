package com.miw.tripplanner.dtos.requests;

import com.miw.tripplanner.dtos.HorarioDto;
import com.miw.tripplanner.dtos.UbicacionDto;

import java.util.List;

public class PlanRequest {
    private Integer id;
    private Integer idViaje;
    private UbicacionDto ubicacion;
    private HorarioDto horario;
    private List<Integer> usuariosPago;
    private Integer pagador;
    private String nombre;
    private Integer importancia;
    private String descripcion;
}
