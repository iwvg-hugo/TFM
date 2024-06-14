package com.miw.tripplanner.services;

import com.miw.tripplanner.dtos.PropuestaDto;
import java.util.List;

public interface PropuestaService {
    List<PropuestaDto> getAllPropuestas();
    PropuestaDto getPropuesta(Integer id);
    Integer createPropuesta(PropuestaDto propuestaDto);
    void updatePropuesta(Integer id, PropuestaDto propuestaDto);
    void deletePropuesta(Integer id);
}
