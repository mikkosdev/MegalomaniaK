package org.mikkosdev.megalomaniak.core;

import org.mikkosdev.mediatorj.IRequest;
import org.mikkosdev.mediatorj.container.Container;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Node {

    protected final Logger logger = LoggerFactory.getLogger(Node.class);
    private ExecutorService executorService;
    private Container container;
    private AbstractActor testActor;

    public Node() {
        executorService = Executors.newVirtualThreadPerTaskExecutor();
    }

    public UUID addActor(AbstractActor actor) {
        testActor = actor;
        return UUID.randomUUID();
    }

    public CompletableFuture<AbstractActor> getActor(UUID uuid) {
        CompletableFuture<AbstractActor> completableFutureResult = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return testActor;
        }, executorService);

        return completableFutureResult;
    }

    // Placeholder method for messages coming from clients
    public void handleNodeMessage(String data) {
        logger.debug("handleNodeMessage called");
    }

    // Placeholder method for messages coming from other cluster nodes
    public void handleClusterMessage(String data) {
        logger.debug("handleClusterMessage called");
    }
}
