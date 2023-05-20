package it.unibs.ui;

@FunctionalInterface
public interface Command {
    /**
     * azione da compiere a comando ricevuto
     */
    void onSelection();
}
