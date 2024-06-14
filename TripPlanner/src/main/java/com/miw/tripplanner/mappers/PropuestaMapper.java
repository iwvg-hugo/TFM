package com.miw.tripplanner.mappers;

import com.miw.tripplanner.dtos.PropuestaDto;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface PropuestaMapper {
    List<PropuestaDto> getAllPropuestas();
    PropuestaDto getPropuesta(Integer id);
    Integer createPropuesta(PropuestaDto propuestaDto);
    void updatePropuesta(Integer id, PropuestaDto propuestaDto);
    void deletePropuesta(Integer id);
}
