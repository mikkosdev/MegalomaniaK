package org.mikkosdev.megalomaniak;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.mikkosdev.megalomaniak.core.AbstractNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class MegalomaniakTest {

    public class MyNode extends AbstractNode {
        protected final Logger logger = LoggerFactory.getLogger(AbstractNode.class);

        @Override
        public void handleNodeMessage(String data) {
            logger.debug("MyNode.handleNodeMessage()");
        }
    }

    @Test
    void createClientReturnsMegalomaniakClient() {
        assertInstanceOf(MegalomaniakClient.class, Megalomaniak.createClient());
    }

    @Test
    void createClientReturnsMegalomaniakServer() {
        int nodePort = 8080;
        int clusterPort = 8088;
        AbstractNode myNode = new MyNode();

        assertInstanceOf(MegalomaniakServer.class, Megalomaniak.createServer(nodePort, clusterPort, myNode));
    }

    @Test
    void callEndpointIsNotImplementedYet() {
        MegalomaniakClient client = Megalomaniak.createClient();

        assertThrows(UnsupportedOperationException.class,
                () -> client.CallEndpoint("/api/players", null));
    }
}