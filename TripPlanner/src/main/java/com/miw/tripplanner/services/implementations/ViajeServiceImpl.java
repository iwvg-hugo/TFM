package com.miw.tripplanner.services.implementations;

import com.miw.tripplanner.dtos.HorarioDto;
import com.miw.tripplanner.dtos.UsuarioViajeDto;
import com.miw.tripplanner.dtos.ViajeDto;
import com.miw.tripplanner.mappers.HorarioMapper;
import com.miw.tripplanner.mappers.UsuarioViajeMapper;
import com.miw.tripplanner.mappers.ViajeMapper;
import com.miw.tripplanner.services.ViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViajeServiceImpl implements ViajeService {

    @Autowired
    private ViajeMapper viajeMapper;

    @Autowired
    private UsuarioViajeMapper usuarioViajeMapper;

    @Autowired
    private HorarioMapper horarioMapper;

    @Override
    public List<ViajeDto> getAllViajes() {
        return viajeMapper.getAllViajes();
    }

    @Override
    public ViajeDto getViaje(Integer id) {
        return viajeMapper.getViaje(id);
    }

    @Override
    public List<ViajeDto> findViajesByUserId(Integer id) {
        return viajeMapper.findViajesByUserId(id);
    }

    @Override
    public Integer createViaje(Integer idUsuario, ViajeDto viajeDto) {
        viajeDto.setIdHorario(horarioMapper.createHorario(new HorarioDto()));
        Integer id = viajeMapper.createViaje(viajeDto);
        usuarioViajeMapper.createUsuarioViaje(new UsuarioViajeDto(idUsuario, id) );
        return id;
    }

    @Override
    public void updateViaje(Integer id, ViajeDto viajeDto) {
        viajeMapper.updateViaje(viajeDto);
    }

    @Override
    public void deleteViaje(Integer id) {
        viajeMapper.deleteViaje(id);
    }
}
