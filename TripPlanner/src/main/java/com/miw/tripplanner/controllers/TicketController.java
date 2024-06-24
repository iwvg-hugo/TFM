package com.miw.tripplanner.controllers;

import com.miw.tripplanner.dtos.TicketDto;
import com.miw.tripplanner.dtos.requests.TicketRequest;
import com.miw.tripplanner.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping()
    List<TicketDto> getTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/{id}")
    TicketDto getTicket(@PathVariable Integer id) {
        return ticketService.getTicket(id);
    }

    @GetMapping("/viaje/{id}")
    List<TicketDto> getTicketsByViaje(@PathVariable Integer id) {
        return ticketService.getTicketsByViaje(id);
    }

    @PostMapping()
    Integer createTicket(@RequestBody TicketRequest ticketRequest) {
        return ticketService.createTicket(ticketRequest.getIdUsuario(), ticketRequest.getTicketDto());
    }

    @PutMapping("/{id}")
    void updateTicket(@PathVariable Integer id, @RequestBody TicketDto ticketDto) {
        ticketService.updateTicket(id, ticketDto);
    }

    @DeleteMapping("/{id}")
    void deleteTicket(@PathVariable Integer id) {
        ticketService.deleteTicket(id);
    }
}
