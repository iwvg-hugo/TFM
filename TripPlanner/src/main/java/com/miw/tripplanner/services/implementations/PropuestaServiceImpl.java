package com.miw.tripplanner.services.implementations;

import com.miw.tripplanner.dtos.PropuestaDto;
import com.miw.tripplanner.mappers.PropuestaMapper;
import com.miw.tripplanner.services.PropuestaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropuestaServiceImpl implements PropuestaService {

    @Autowired
    private PropuestaMapper propuestaMapper;

    public List<PropuestaDto> getAllPropuestas() {
        return propuestaMapper.getAllPropuestas();
    }

    public PropuestaDto getPropuesta(Integer id) {
        return propuestaMapper.getPropuesta(id);
    }

    public Integer createPropuesta(PropuestaDto propuestaDto) {
        return propuestaMapper.createPropuesta(propuestaDto);
    }

    public void updatePropuesta(Integer id, PropuestaDto propuestaDto) {
        propuestaMapper.updatePropuesta(id, propuestaDto);
    }

    public void deletePropuesta(Integer id) {
        propuestaMapper.deletePropuesta(id);
    }
}
