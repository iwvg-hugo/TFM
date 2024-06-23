package com.miw.tripplanner.mappers;

import com.miw.tripplanner.dtos.PagoDto;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface PagoMapper {
    List<PagoDto> getAllPagos();
    List<PagoDto> getPagosByIdViaje(Integer id);
    PagoDto getPago(Integer id);
    Integer createPago(PagoDto pagoDto);
    void updatePago(Integer id, PagoDto pagoDto);
    void deletePago(Integer id);
    void deletePagoByIdPlan(Integer idPlan);}
