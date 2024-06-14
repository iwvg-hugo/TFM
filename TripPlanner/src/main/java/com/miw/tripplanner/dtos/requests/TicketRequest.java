package com.miw.tripplanner.dtos.requests;

import com.miw.tripplanner.dtos.TicketDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TicketRequest {
    private Integer idUsuario;
    private TicketDto ticketDto;
}
