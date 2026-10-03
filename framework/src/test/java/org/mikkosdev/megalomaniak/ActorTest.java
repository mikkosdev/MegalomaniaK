package org.mikkosdev.megalomaniak;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mikkosdev.mediatorj.IRequest;
import org.mikkosdev.megalomaniak.core.AbstractActor;
import org.mikkosdev.megalomaniak.core.Node;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

public class ActorTest {

    protected final Logger logger = LoggerFactory.getLogger(ActorTest.class);

    private Node node;
    private MyActor myActor;
    private MyRequest myRequest;

    class MyActor extends AbstractActor {
        public MyActor() {
            super();
        }

        @Override
        public void processMessage(IRequest request) {
            logger.debug("handleMessage() called");
        }
    }

    class MyRequest implements IRequest {

        public MyRequest() {
        }
    }

    @BeforeEach
    public void setUp() {
        node = new Node();
        myRequest = new MyRequest();
        myActor = new MyActor();
    }

    @AfterEach
    public void tearDown() {
        node = null;
    }

    @Test
    public void sendMessageToActor() {
        MyActor myActorSpy = Mockito.spy(myActor);

        myActorSpy.sendMessage(myRequest);
        // There should be now 1 message in the inbox
        assertEquals(1, myActor.getInboxSize());

        // Process one message
        myActorSpy.run();

        // There should be now no messages in the inbox
        assertEquals(0, myActor.getInboxSize());

        // Check that message was processed
        Mockito.verify(myActorSpy).processMessage(any(IRequest.class));
    }
}
