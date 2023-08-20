package it.unibs.core;

import java.util.List;

public abstract class Publisher {
    private final List<Subscriber> subscribers;

    public Publisher(List<Subscriber> subscribers) {
        this.subscribers = subscribers;
    }

    public void attach(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void detach(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public <T extends Publisher> void notifySubscribers(T context) {
        for (Subscriber subscriber : subscribers) {
            subscriber.update(context);
        }
    }
}
