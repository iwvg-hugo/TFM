package com.miw.tripplanner.dtos;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class ViajeDtoTest {

    @Test
    void testEqualsAndHashCode() {
        EqualsVerifier.simple().forClass(ViajeDto.class)
                .withRedefinedSuperclass() // Permite que la clase no sea final
                .verify();
    }
}
