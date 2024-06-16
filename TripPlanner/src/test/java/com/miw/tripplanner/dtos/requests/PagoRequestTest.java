package com.miw.tripplanner.dtos.requests;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class PagoRequestTest {

    @Test
    void testEqualsAndHashCode() {
        EqualsVerifier.simple().forClass(PagoRequest.class)
                .withRedefinedSuperclass() // Permite que la clase no sea final
                .verify();
    }
}
