package org.mikkosdev.megalomaniak.observer;

public interface IObserver {

    public abstract void sendNotification();
    public abstract void sendNotification(Object o);
}
