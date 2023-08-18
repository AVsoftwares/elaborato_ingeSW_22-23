package it.unibs.ui;

/**
 * Classe usata per la creazione di una entry di un menu
 * di navigazione
 */
public class BaseMenuEntry {

    private final String description;
    private Command command;

    /**
     * @param description breve descrizione testuale del comando associato alla entry
     * @param command     comando associato alla entry
     * @see Command
     */
    public BaseMenuEntry(String description, Command command) {
        this.description = description;
        this.command = command;
    }

    public BaseMenuEntry(String description) {
        this(description, () -> {
            throw new UnsupportedOperationException("A menu entry requires a Command, but it's not been set");
        });
    }

    public String getDescription() {
        return description;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }
}