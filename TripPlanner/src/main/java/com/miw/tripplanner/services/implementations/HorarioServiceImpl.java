package com.miw.tripplanner.services.implementations;

import com.miw.tripplanner.dtos.HorarioDto;
import com.miw.tripplanner.mappers.HorarioMapper;
import com.miw.tripplanner.services.HorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HorarioServiceImpl implements HorarioService {

    @Autowired
    private HorarioMapper horarioMapper;

    @Override
    public List<HorarioDto> getAllHorarios() {
        return horarioMapper.getAllHorarios();
    }

    @Override
    public HorarioDto getHorario(Integer id) {
        return horarioMapper.getHorario(id);
    }

    @Override
    public Integer createHorario(HorarioDto horarioDto) {
        return horarioMapper.createHorario(horarioDto);
    }

    @Override
    public void updateHorario(Integer id, HorarioDto horarioDto) {
        horarioMapper.updateHorario(id, horarioDto);
    }

    @Override
    public void deleteHorario(Integer id) {
        horarioMapper.deleteHorario(id);
    }
}
