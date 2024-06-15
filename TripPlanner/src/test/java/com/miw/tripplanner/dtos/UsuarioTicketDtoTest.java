package com.miw.tripplanner.dtos;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class UsuarioTicketDtoTest {
    @Test
    void testEqualsAndHashCode() {
        EqualsVerifier.simple().forClass(UsuarioTicketDto.class)
                .withRedefinedSuperclass() // Permite que la clase no sea final
                .verify();
    }
}
