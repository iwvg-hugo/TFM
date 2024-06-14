package com.miw.tripplanner.services;

import com.miw.tripplanner.dtos.HorarioDto;
import java.util.List;

public interface HorarioService {
    List<HorarioDto> getAllHorarios();
    HorarioDto getHorario(Integer id);
    Integer createHorario(HorarioDto horarioDto);
    void updateHorario(Integer id, HorarioDto horarioDto);
    void deleteHorario(Integer id);
}
