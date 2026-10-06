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
    void createClientReturnsMegalomaniakServer() {
        int nodePort = 8080;
        int clusterPort = 8088;

        assertInstanceOf(MegalomaniakServer.class, Megalomaniak.createServer(nodePort, clusterPort));
    }

    @Test
    void callEndpointIsNotImplementedYet() {
        MegalomaniakClient client = Megalomaniak.createClient();

        assertThrows(UnsupportedOperationException.class,
                () -> client.CallEndpoint("/api/players", null));
    }
}