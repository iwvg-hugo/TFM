package com.miw.tripplanner.services.implementations;

import com.miw.tripplanner.dtos.PagoDto;
import com.miw.tripplanner.dtos.UsuarioDto;
import com.miw.tripplanner.dtos.UsuarioPagoDto;
import com.miw.tripplanner.dtos.detalle.PagoDetalleDto;
import com.miw.tripplanner.mappers.PagoMapper;
import com.miw.tripplanner.mappers.UsuarioMapper;
import com.miw.tripplanner.mappers.UsuarioPagoMapper;
import com.miw.tripplanner.services.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagoServiceImpl implements PagoService {

    @Autowired
    private PagoMapper pagoMapper;

    @Autowired
    private UsuarioPagoMapper usuarioPagoMapper;

    @Autowired
    private UsuarioMapper usuarioMapper;

    public List<PagoDto> getAllPagos() {
        return pagoMapper.getAllPagos();
    }

    public PagoDetalleDto getPago(Integer id) {
        PagoDto pago = pagoMapper.getPago(id);
        PagoDetalleDto pagoDetalle = new PagoDetalleDto();
        pagoDetalle.setId(pago.getId());
        pagoDetalle.setTotal(pago.getTotal());
        for (UsuarioPagoDto usuarioPago : usuarioPagoMapper.findUsuariosPagosByIdPago(id)) {
            UsuarioDto usuario = usuarioMapper.getUsuario(usuarioPago.getIdUsuario());
            usuario.setPassword("");
            pagoDetalle.getUsuarios().add(usuario);

            if (Boolean.TRUE.equals(usuarioPago.getPagador())) {
                pagoDetalle.setIdPagador(usuarioPago.getIdUsuario());
            }
        }

        return pagoDetalle;
    }

    public Integer createPago(Integer idUsuario, PagoDto pagoDto, Boolean pagador) {
        Integer idPago = pagoMapper.createPago(pagoDto);

        usuarioPagoMapper.createUsuarioPago(
                new UsuarioPagoDto(idUsuario, idPago, pagador)
        );

        return idPago;
    }

    public void updatePago(Integer id, PagoDto pagoDto) {
        pagoMapper.updatePago(id, pagoDto);
    }

    public void deletePago(Integer id) {
        usuarioPagoMapper.deleteUsuarioPagoByIdPago(id);
        pagoMapper.deletePago(id);
    }
}
