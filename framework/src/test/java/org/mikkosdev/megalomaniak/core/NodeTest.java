package org.mikkosdev.megalomaniak.core;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mikkosdev.mediatorj.IRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mikkosdev.megalomaniak.util.ConcurrencyUtils.await;

public class NodeTest {

    private AbstractNode myNode;

    public class MyNode extends AbstractNode {
        protected final Logger logger = LoggerFactory.getLogger(AbstractNode.class);

        @Override
        public void handleNodeMessage(String data) {
            logger.debug("MyNode.handleNodeMessage()");
        }
    }

    class MyActor extends AbstractActor {
        public MyActor() {
            super();
        }

        @Override
        public void processMessage(IRequest request) {
            logger.debug("handleMessage() called");
        }
    }

    @BeforeEach
    public void setUp() {
        myNode = new MyNode();
    }

    @AfterEach
    public void tearDown() {
        myNode = null;
    }

    @Test
    public void testGettingAnActor() {
        var actor = new MyActor();
        var uuid = myNode.addActor(actor);

        CompletableFuture<AbstractActor> actorFuture = myNode.getActor(uuid);
        MyActor fetchedActor = (MyActor) await(actorFuture);

        assertNotNull(fetchedActor);
    }
}
