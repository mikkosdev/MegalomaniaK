package org.mikkosdev.megalomaniak.core;

import org.mikkosdev.mediatorj.container.AddressableObject;
import org.mikkosdev.megalomaniak.observer.AbstractObserver;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractActor extends AddressableObject implements Runnable {

    protected List<AbstractObserver> observers = new ArrayList<>();

    @Override
    public void run() {

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
