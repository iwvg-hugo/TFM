package com.miw.tripplanner.services.implementations;

import com.miw.tripplanner.dtos.*;
import com.miw.tripplanner.dtos.detalle.ViajeDetalleDto;
import com.miw.tripplanner.dtos.requests.ViajeRequest;
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
    @Autowired
    private UsuarioMapper usuarioMapper;

    @Override
    public List<ViajeDto> getAllViajes() {
        return viajeMapper.getAllViajes();
    }

    @Override
    public ViajeDetalleDto getViaje(Integer id) {
        ViajeDto viaje = this.viajeMapper.getViaje(id);
        ViajeDetalleDto viajeDetalle = new ViajeDetalleDto();
        viajeDetalle.setId(viaje.getId());
        viajeDetalle.setHorario(this.horarioMapper.getHorario(viaje.getIdHorario()));
        viajeDetalle.setPropuestas(this.propuestaMapper.findPropuestasByIdViaje(viaje.getId()));
        viajeDetalle.setPlanes(this.planService.findPlanesByIdViaje(viaje.getId()));
        viajeDetalle.setTitulo(viaje.getTitulo());
        viajeDetalle.setEmailParticipantes(this.usuarioViajeMapper.getUsuariosViaje(viaje.getId()));
        return viajeDetalle;

    }

    @Override
    public List<ViajeDto> findViajesByUserId(Integer id) {
        return viajeMapper.findViajesByUserId(id);
    }

    @Override
    public Integer createViaje(ViajeRequest viajeRequest) {
        ViajeDto viajeDto = new ViajeDto();
        viajeDto.setIdHorario(horarioMapper.createHorario(new HorarioDto(0, viajeRequest.getFechaInicio(), viajeRequest.getFechaFin())));
        viajeDto.setTitulo(viajeRequest.getTitulo());
        viajeDto.setImagen(viajeRequest.getImagen());
        Integer id = viajeMapper.createViaje(viajeDto);
        UsuarioDto usuarioDto = usuarioMapper.findUsuarioByEmail(viajeRequest.getUserEmail());
        if (usuarioDto != null) {
            usuarioViajeMapper.createUsuarioViaje(new UsuarioViajeDto(usuarioDto.getId(), id));
        }
        for (String email : viajeRequest.getEmailParticipantes()) {
            Integer idParticipante = usuarioMapper.findUsuarioByEmail(email).getId();
            if (idParticipante != null) {
                usuarioViajeMapper.createUsuarioViaje(new UsuarioViajeDto(idParticipante, id));
            }
        }
        for (PropuestaDto propuestaDto : viajeRequest.getPropuestas()) {
            propuestaDto.setIdViaje(id);
            propuestaMapper.createPropuesta(propuestaDto);
        }
        return id;
    }

    @Override
    public void updateViaje(Integer id, ViajeRequest viajeDto) {
        ViajeDto viaje = new ViajeDto();
        viaje.setId(id);
        viaje.setTitulo(viajeDto.getTitulo());
        viaje.setIdHorario(horarioMapper.createHorario(new HorarioDto(0, viajeDto.getFechaInicio(), viajeDto.getFechaFin())));

        Integer idUsuario = usuarioMapper.findUsuarioByEmail(viajeDto.getUserEmail()).getId();
        usuarioViajeMapper.deleteUsuariosViajesByIdViaje(id);

        if (idUsuario != null) {
            usuarioViajeMapper.createUsuarioViaje(new UsuarioViajeDto(idUsuario, id));
        }

        for (String email : viajeDto.getEmailParticipantes()) {
            Integer idParticipante = usuarioMapper.findUsuarioByEmail(email).getId();
            if (idParticipante != null) {
                usuarioViajeMapper.createUsuarioViaje(new UsuarioViajeDto(idParticipante, id));
            }
        }

        propuestaMapper.deletePropuestasByIdViaje(id);

        for (PropuestaDto propuestaDto : viajeDto.getPropuestas()) {
            propuestaDto.setIdViaje(id);
            propuestaMapper.createPropuesta(propuestaDto);
        }

        viajeMapper.updateViaje(viaje);
    }

    @Override
    public void deleteViaje(Integer id) {
        ViajeDto viajeABorrar = viajeMapper.getViaje(id);
        propuestaMapper.deletePropuestasByIdViaje(id);
        usuarioViajeMapper.deleteUsuariosViajesByIdViaje(id);
        planService.deletePlanesByIdViaje(id);
        viajeMapper.deleteViaje(id);
        horarioMapper.deleteHorario(viajeABorrar.getIdHorario());
    }
}
