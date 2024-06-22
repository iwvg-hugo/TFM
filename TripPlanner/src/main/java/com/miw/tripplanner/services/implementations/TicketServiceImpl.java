package com.miw.tripplanner.services.implementations;

import com.miw.tripplanner.dtos.PlanDto;
import com.miw.tripplanner.dtos.TicketDto;
import com.miw.tripplanner.dtos.UsuarioTicketDto;
import com.miw.tripplanner.mappers.*;
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

    @Autowired
    private HorarioMapper horarioMapper;

    @Autowired
    private UbicacionMapper ubicacionMapper;

    @Autowired
    private PlanMapper planMapper;

    public List<TicketDto> getAllTickets() {
        return ticketMapper.getAllTickets();
    }

    public TicketDto getTicket(Integer id) {
        TicketDto ticket = ticketMapper.getTicket(id);
        PlanDto plan = planMapper.getPlan(ticket.getIdPlan());
        ticket.setHorario(horarioMapper.getHorario(plan.getIdHorario()));
        ticket.setUbicacion(ubicacionMapper.getUbicacion(plan.getIdUbicacion()));
        return ticket;
    }

    public List<TicketDto> getTicketsByViaje(Integer id) {
        List<TicketDto> tickets = ticketMapper.getTicketsByViaje(id);
        for (TicketDto ticket : tickets) {
            PlanDto plan = planMapper.getPlan(ticket.getIdPlan());
            ticket.setHorario(horarioMapper.getHorario(plan.getIdHorario()));
            ticket.setUbicacion(ubicacionMapper.getUbicacion(plan.getIdUbicacion()));
        }
        return tickets;
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
        usuarioTicketMapper.deleteUsuarioTicketByIdTicket(id);
        ticketMapper.deleteTicket(id);
    }

}
