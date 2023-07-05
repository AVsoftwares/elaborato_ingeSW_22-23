package it.unibs.ui;

/**
 * Classe usata per la creazione di una entry di un menu
 * di navigazione
 */
public class MenuEntry {

    private final String description;
    private final Command command;

    /**
     * @param description breve descrizione testuale del comando associato alla entry
     * @param command     comando associato alla entry
     * @see Command
     */
    public MenuEntry(String description, Command command) {
        this.description = description;
        this.command = command;
    }

    public String getDescription() {
        return description;
    }

    public Command getCommand() {
        return command;
    }
}