package com.miw.tripplanner.dtos;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class UsuarioDtoTest {
    @Test
    void testEqualsAndHashCode() {
        EqualsVerifier.simple().forClass(UsuarioDto.class)
                .withRedefinedSuperclass() // Permite que la clase no sea final
                .verify();
    }
}
