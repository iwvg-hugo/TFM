package com.miw.tripplanner.services;

import com.miw.tripplanner.dtos.TicketDto;
import java.util.List;

public interface TicketService {
    List<TicketDto> getAllTickets();
    TicketDto getTicket(Integer id);
    Integer createTicket(Integer idUsuario, TicketDto ticketDto);
    void updateTicket(Integer id, TicketDto ticketDto);
    void deleteTicket(Integer id);
}
