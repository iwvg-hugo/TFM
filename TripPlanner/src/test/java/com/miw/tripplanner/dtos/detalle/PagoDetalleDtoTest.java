package com.miw.tripplanner.dtos.detalle;

import com.miw.tripplanner.dtos.requests.PagoRequest;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class PagoDetalleDtoTest {

    @Test
    void testEqualsAndHashCode() {
        EqualsVerifier.simple().forClass(PagoDetalleDto.class)
                .withRedefinedSuperclass() // Permite que la clase no sea final
                .verify();
    }
}
