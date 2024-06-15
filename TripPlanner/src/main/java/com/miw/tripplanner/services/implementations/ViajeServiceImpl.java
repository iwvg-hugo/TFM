package com.miw.tripplanner.services.implementations;

import com.miw.tripplanner.dtos.HorarioDto;
import com.miw.tripplanner.dtos.UsuarioViajeDto;
import com.miw.tripplanner.dtos.ViajeDto;
import com.miw.tripplanner.dtos.detalle.ViajeDetalleDto;
import com.miw.tripplanner.mappers.*;
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

    @Autowired
    private PropuestaMapper propuestaMapper;

    @Autowired
    private PlanServiceImpl planService;

    @Override
    public List<ViajeDto> getAllViajes() {
        return this.viajeMapper.getAllViajes();
    }

    @Override
    public ViajeDetalleDto getViaje(Integer id) {
        ViajeDto viaje = this.viajeMapper.getViaje(id);
        ViajeDetalleDto viajeDetalle = new ViajeDetalleDto();
        viajeDetalle.setId(viaje.getId());
        viajeDetalle.setHorario(this.horarioMapper.getHorario(viaje.getIdHorario()));
        viajeDetalle.setPropuestas(this.propuestaMapper.findPropuestasByIdViaje(viaje.getId()));
        viajeDetalle.setPlanes(this.planService.getPlanesByIdViaje(viaje.getId()));
        return viajeDetalle;
    }

    @Override
    public List<ViajeDto> findViajesByUserId(Integer id) {
        return this.viajeMapper.findViajesByUserId(id);
    }

    @Override
    public Integer createViaje(Integer idUsuario, ViajeDto viajeDto) {
        viajeDto.setIdHorario(this.horarioMapper.createHorario(new HorarioDto()));
        Integer id = this.viajeMapper.createViaje(viajeDto);
        this.usuarioViajeMapper.createUsuarioViaje(new UsuarioViajeDto(idUsuario, id) );
        return id;
    }

    @Override
    public void updateViaje(Integer id, ViajeDto viajeDto) {
        this.viajeMapper.updateViaje(viajeDto);
    }

    @Override
    public void deleteViaje(Integer id) {
        this.horarioMapper.deleteHorario(this.viajeMapper.getViaje(id).getIdHorario());
        this.propuestaMapper.deletePropuestasByIdViaje(id);
        this.usuarioViajeMapper.deleteUsuariosViajesByIdViaje(id);
        this.planService.deletePlanesByIdViaje(id);
        this.viajeMapper.deleteViaje(id);
    }
}
