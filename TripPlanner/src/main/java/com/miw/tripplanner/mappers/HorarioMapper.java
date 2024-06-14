package com.miw.tripplanner.mappers;

import com.miw.tripplanner.dtos.HorarioDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HorarioMapper {

    List<HorarioDto> getAllHorarios();
    HorarioDto getHorario(Integer id);
    Integer createHorario(HorarioDto horarioDto);
    void updateHorario(Integer id, HorarioDto horarioDto);
    void deleteHorario(Integer id);

}

