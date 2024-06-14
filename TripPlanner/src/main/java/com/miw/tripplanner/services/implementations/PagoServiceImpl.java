package com.miw.tripplanner.services.implementations;

import com.miw.tripplanner.dtos.PagoDto;
import com.miw.tripplanner.dtos.UsuarioPagoDto;
import com.miw.tripplanner.mappers.PagoMapper;
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

    public List<PagoDto> getAllPagos() {
        return pagoMapper.getAllPagos();
    }

    public PagoDto getPago(Integer id) {
        return pagoMapper.getPago(id);
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
        pagoMapper.deletePago(id);
    }
}
