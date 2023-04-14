package it.unibs.ui;

import it.unibs.ui.commands.manager.Command;
import lombok.Data;

@Data
public class MenuEntry {
    private final String description;
    private final Command command;

    public MenuEntry(String description, Command command) {
        this.description = description;
        this.command = command;
    }
}