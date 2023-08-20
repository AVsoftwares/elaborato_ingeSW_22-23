package it.unibs.core;

public interface Subscriber {
    <T extends Publisher> void update(T context);
}
