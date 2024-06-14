package com.miw.tripplanner.services.implementations;

import com.miw.tripplanner.dtos.TicketDto;
import com.miw.tripplanner.dtos.UsuarioTicketDto;
import com.miw.tripplanner.mappers.TicketMapper;
import com.miw.tripplanner.mappers.UsuarioTicketMapper;
import com.miw.tripplanner.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketMapper ticketMapper;

    @Autowired
    private UsuarioTicketMapper usuarioTicketMapper;

    public List<TicketDto> getAllTickets() {
        return ticketMapper.getAllTickets();
    }

    public TicketDto getTicket(Integer id) {
        return ticketMapper.getTicket(id);
    }

    public Integer createTicket(Integer idUsuario, TicketDto ticketDto) {
        Integer idTicket = ticketMapper.createTicket(ticketDto);

        usuarioTicketMapper.createUsuarioTicket(
                new UsuarioTicketDto(idUsuario, idTicket)
        );

        return idTicket;
    }

    public void updateTicket(Integer id, TicketDto ticketDto) {
        ticketMapper.updateTicket(id, ticketDto);
    }

    public void deleteTicket(Integer id) {
        ticketMapper.deleteTicket(id);
    }
}
