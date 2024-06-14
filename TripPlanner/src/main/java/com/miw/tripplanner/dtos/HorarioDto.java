package com.miw.tripplanner.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class HorarioDto {
    private Integer id;
    private Timestamp inicio;
    private Timestamp fin;
}

