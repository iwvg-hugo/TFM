package com.miw.tripplanner.mappers;

import com.miw.tripplanner.dtos.UsuarioTicketDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UsuarioTicketMapper {
    List<UsuarioTicketDto> getAllUsuariosTickets();
    UsuarioTicketDto getUsuarioTicket(Integer id);
    Integer createUsuarioTicket(UsuarioTicketDto usuarioTicketDto);
    void updateUsuarioTicket(Integer id, UsuarioTicketDto usuarioTicketDto);
    void deleteUsuarioTicket(Integer id);
}
