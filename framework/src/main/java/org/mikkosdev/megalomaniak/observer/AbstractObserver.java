package org.mikkosdev.megalomaniak.observer;

public abstract class AbstractObserver {

    public abstract void sendNotification();
    public abstract void sendNotification(Object o);
}
