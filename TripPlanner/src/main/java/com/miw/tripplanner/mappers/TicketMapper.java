package com.miw.tripplanner.mappers;

import com.miw.tripplanner.dtos.TicketDto;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface TicketMapper {
        List<TicketDto> getAllTickets();
        TicketDto getTicket(Integer id);
        Integer createTicket(TicketDto ticketDto);
        void updateTicket(Integer id, TicketDto ticketDto);
        void deleteTicket(Integer id);
}
