package com.miw.tripplanner.dtos;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class HorarioDtoTest {

    @Test
    void testEqualsAndHashCode() {
        EqualsVerifier.simple().forClass(HorarioDto.class)
                .withRedefinedSuperclass()
                .verify();
    }
}
