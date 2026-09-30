package org.mikkosdev.megalomaniak;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class MegalomaniakTest {

    @Test
    void createClientReturnsMegalomaniakClient() {
        assertInstanceOf(MegalomaniakClient.class, Megalomaniak.createClient());
    }

    @Test
    void callEndpointIsNotImplementedYet() {
        MegalomaniakClient client = Megalomaniak.createClient();

        assertThrows(UnsupportedOperationException.class,
                () -> client.CallEndpoint("/api/players", null));
    }
}