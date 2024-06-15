package com.miw.tripplanner.dtos;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class PagoDtoTest {

    @Test
    void testEqualsAndHashCode() {
        EqualsVerifier.simple().forClass(PagoDto.class)
                .withRedefinedSuperclass() // Permite que la clase no sea final
                .verify();
    }
}
