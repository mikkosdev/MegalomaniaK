package org.mikkosdev.megalomaniak.core;

import org.mikkosdev.mediatorj.container.Container;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class AbstractNode {

    protected final Logger logger = LoggerFactory.getLogger(AbstractNode.class);
    private ExecutorService executorService;
    private Container container;
    private AbstractActor testActor;

    public AbstractNode() {
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

    /**
     * This is the most important handler method for the cluster node.
     * It has to be implemented by the derived class.
     *
     * Implement this method to handle the incoming messages from client.
     * @param data String data that was received from the socket.
     */
    public abstract void handleNodeMessage(String data);

    // Placeholder method for messages coming from other cluster nodes
    public void handleClusterMessage(String data) {
        logger.debug("handleClusterMessage called");
    }
}
