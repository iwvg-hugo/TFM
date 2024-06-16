package com.miw.tripplanner.mappers;

import com.miw.tripplanner.dtos.UsuarioTicketDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UsuarioTicketMapper {
    List<UsuarioTicketDto> getAllUsuariosTickets();
    UsuarioTicketDto getUsuarioTicket(Integer idUsuario, Integer idTicket);
    Integer createUsuarioTicket(UsuarioTicketDto usuarioTicketDto);
    void deleteUsuarioTicket(Integer idUsuario, Integer idTicket);
    void deleteUsuarioTicketByIdTicket(Integer idTicket);
}
