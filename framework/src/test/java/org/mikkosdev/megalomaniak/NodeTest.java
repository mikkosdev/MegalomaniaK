package org.mikkosdev.megalomaniak;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mikkosdev.megalomaniak.core.Node;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class NodeTest {

    private Node node;

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
        var uuid = UUID.randomUUID();
        var actor = node.getActor(uuid);

        assertNotNull(actor);
    }
}
