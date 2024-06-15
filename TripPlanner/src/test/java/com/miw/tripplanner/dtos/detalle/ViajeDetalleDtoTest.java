package com.miw.tripplanner.dtos.detalle;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class ViajeDetalleDtoTest {

    @Test
    void testEqualsAndHashCode() {
        EqualsVerifier.simple().forClass(ViajeDetalleDto.class)
                .withRedefinedSuperclass() // Permite que la clase no sea final
                .verify();
    }
}
