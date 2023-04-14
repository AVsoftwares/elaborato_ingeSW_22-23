package it.unibs.ui.commands.manager;

@FunctionalInterface
public interface Command {
    void onSelection();
}
