package org.mikkosdev.megalomaniak;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class MegalomaniakIT {

    @Test
    void clientCanBeCreatedFromTheIntegrationTestSourceSet() {
        assertNotNull(Megalomaniak.createClient());
    }
}
