package it.unibs.ui;

import lombok.Getter;
import lombok.Setter;

/**
 * Classe usata per la creazioe di una entry di un menu
 * di navigazione
 */
@Getter
@Setter
public class MenuEntry {

    private final String description;
    private final Command command;
    /**
     * @param description breve descrizione testuale del comando associato alla entry
     * @param command comando associato alla entry
     * @see Command
     */
    public MenuEntry(String description, Command command) {
        this.description = description;
        this.command = command;
    }
}