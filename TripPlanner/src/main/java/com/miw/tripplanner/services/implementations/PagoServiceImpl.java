package com.miw.tripplanner.services.implementations;

import com.miw.tripplanner.dtos.PagoDto;
import com.miw.tripplanner.dtos.UsuarioDto;
import com.miw.tripplanner.dtos.UsuarioPagoDto;
import com.miw.tripplanner.dtos.detalle.PagoDetalleDto;
import com.miw.tripplanner.dtos.requests.PagoRequest;
import com.miw.tripplanner.mappers.HorarioMapper;
import com.miw.tripplanner.mappers.PagoMapper;
import com.miw.tripplanner.mappers.UsuarioMapper;
import com.miw.tripplanner.mappers.UsuarioPagoMapper;
import com.miw.tripplanner.services.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PagoServiceImpl implements PagoService {

    @Autowired
    private PagoMapper pagoMapper;

    @Autowired
    private UsuarioPagoMapper usuarioPagoMapper;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private HorarioMapper horarioMapper;

    @Override
    public List<PagoDetalleDto> getAllPagos() {
        List<PagoDto> pagos = pagoMapper.getAllPagos();
        List<PagoDetalleDto> pagosDetalle = new ArrayList<>();
        for (PagoDto pago : pagos) {
            pagosDetalle.add(convertToPagoDetalleDto(pago));
        }
        return pagosDetalle;
    }

    @Override
    public List<PagoDetalleDto> getPagosByIdViaje(Integer id) {
        List<PagoDto> pagos = pagoMapper.getPagosByIdViaje(id);
        List<PagoDetalleDto> pagosDetalle = new ArrayList<>();
        for (PagoDto pago : pagos) {
            pagosDetalle.add(convertToPagoDetalleDto(pago));
        }
        return pagosDetalle;
    }

    @Override
    public PagoDetalleDto getPago(Integer id) {
        PagoDto pago = pagoMapper.getPago(id);
        return convertToPagoDetalleDto(pago);
    }

    @Override
    public Integer createPago(PagoRequest pagoRequest) {
        Integer idHorario = horarioMapper.createHorario(pagoRequest.getPagoDto().getHorario());
        pagoRequest.getPagoDto().setIdHorario(idHorario);
        Integer idPago = pagoMapper.createPago(pagoRequest.getPagoDto());

        // Asignar el pagador principal
        usuarioPagoMapper.createUsuarioPago(new UsuarioPagoDto(pagoRequest.getIdUsuario(), idPago, pagoRequest.getPagador()));

        // Asignar los demás usuarios implicados
        for (UsuarioDto usuario : pagoRequest.getUsuariosImplicados()) {
            usuarioPagoMapper.createUsuarioPago(new UsuarioPagoDto(usuario.getId(), idPago, false));
        }

        return idPago;
    }

    @Override
    public void updatePago(Integer id, PagoDto pagoDto) {
        pagoMapper.updatePago(id, pagoDto);
    }

    @Override
    public void deletePago(Integer id) {
        usuarioPagoMapper.deleteUsuarioPagoByIdPago(id);
        pagoMapper.deletePago(id);
    }

    private PagoDetalleDto convertToPagoDetalleDto(PagoDto pago) {
        PagoDetalleDto pagoDetalle = new PagoDetalleDto();
        pagoDetalle.setId(pago.getId());
        pagoDetalle.setTotal(pago.getTotal());
        pagoDetalle.setDescripcion(pago.getDescripcion());
        pagoDetalle.setHorario(horarioMapper.getHorario(pago.getIdHorario()));
        for (UsuarioPagoDto usuarioPago : usuarioPagoMapper.findUsuariosPagosByIdPago(pago.getId())) {
            UsuarioDto usuario = usuarioMapper.getUsuario(usuarioPago.getIdUsuario());
            usuario.setPassword("");
            usuario.setPagador(usuarioPago.getPagador());
            pagoDetalle.getUsuarios().add(usuario);
            if (Boolean.TRUE.equals(usuarioPago.getPagador())) {
                pagoDetalle.setIdPagador(usuarioPago.getIdUsuario());
            }
        }
        return pagoDetalle;
    }
}