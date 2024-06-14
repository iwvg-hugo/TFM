package com.miw.tripplanner.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class TicketDto {
    private Integer id;
    private Integer idPlan;
    private String nombre;
    private String asiento;
    private byte[] documento;
    private String qr;
}
