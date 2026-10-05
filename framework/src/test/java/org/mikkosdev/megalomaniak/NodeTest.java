package org.mikkosdev.megalomaniak;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mikkosdev.mediatorj.IRequest;
import org.mikkosdev.megalomaniak.core.AbstractActor;
import org.mikkosdev.megalomaniak.core.Node;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mikkosdev.megalomaniak.util.ConcurrencyUtils.await;

public class NodeTest {

    private Node node;

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
        node = new Node();
    }

    @AfterEach
    public void tearDown() {
        node = null;
    }

    @Test
    public void testGettingAnActor() {
        var actor = new MyActor();
        var uuid = node.addActor(actor);

        CompletableFuture<AbstractActor> actorFuture = node.getActor(uuid);
        MyActor fetchedActor = (MyActor) await(actorFuture);

        assertNotNull(fetchedActor);
    }
}
