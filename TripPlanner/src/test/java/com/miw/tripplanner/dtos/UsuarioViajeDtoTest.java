package com.miw.tripplanner.dtos;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class UsuarioViajeDtoTest {
    @Test
    void testEqualsAndHashCode() {
        EqualsVerifier.simple().forClass(UsuarioViajeDto.class)
                .withRedefinedSuperclass() // Permite que la clase no sea final
                .verify();
    }
}
