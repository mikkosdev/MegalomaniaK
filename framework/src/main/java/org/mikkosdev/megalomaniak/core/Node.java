package org.mikkosdev.megalomaniak.core;

import org.mikkosdev.mediatorj.container.Container;

import java.util.UUID;

public class Node {

    private Container container;

    public Object getActor(UUID uuid) {
        return new AbstractActor() {
        };
    }
}
