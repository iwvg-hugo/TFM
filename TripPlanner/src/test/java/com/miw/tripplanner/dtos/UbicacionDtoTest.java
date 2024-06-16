package com.miw.tripplanner.dtos;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class UbicacionDtoTest {
    @Test
    void testEqualsAndHashCode() {
        EqualsVerifier.simple().forClass(UbicacionDto.class)
                .withRedefinedSuperclass() // Permite que la clase no sea final
                .verify();
    }
}
