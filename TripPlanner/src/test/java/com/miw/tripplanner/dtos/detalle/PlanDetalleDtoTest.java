package com.miw.tripplanner.dtos.detalle;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class PlanDetalleDtoTest {

    @Test
    void testEqualsAndHashCode() {
        EqualsVerifier.simple().forClass(PlanDetalleDto.class)
                .withRedefinedSuperclass() // Permite que la clase no sea final
                .verify();
    }
}
