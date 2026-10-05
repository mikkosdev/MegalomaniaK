package org.mikkosdev.megalomaniak.core;

import org.mikkosdev.mediatorj.IRequest;
import org.mikkosdev.megalomaniak.observer.AbstractObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class AbstractActor implements Runnable {

    protected final Logger logger = LoggerFactory.getLogger(AbstractActor.class);
    protected List<AbstractObserver> observers = new ArrayList<>();
    protected ConcurrentLinkedQueue<IRequest> inbox = new ConcurrentLinkedQueue<>();
    protected Thread thread;

    public AbstractActor() {
        logger.debug("In AbstractActor()");
    }

    @Override
    public void run() {
        logger.debug("In run()");

        // Fall-through logic
        if (inbox.isEmpty()) {
            logger.debug("Nothing to process");
            return;
        }

        var message = inbox.poll();
        processMessage(message);
    }

    public void sendMessage(IRequest request) {
        logger.debug("In sendMessage()");
        inbox.offer(request);
    }

    // This method actually processes the message and has to be defined by the derived class
    protected abstract void processMessage(IRequest request);

    public int getInboxSize() {
        return inbox.size();
    }

    public void registerObserver(AbstractObserver observer) {
        observers.add(observer);
    }

    protected void notifyObservers() {
        observers.forEach((o) -> {
            o.sendNotification();
        });
    }

    protected void notifyObservers(Object notification) {
        observers.forEach((o) -> {
            o.sendNotification(notification);
        });
    }
}
