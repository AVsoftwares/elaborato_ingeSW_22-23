package it.unibs.ui;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuEntry {
    private final String description;
    private final Command command;

    public MenuEntry(String description, Command command) {
        this.description = description;
        this.command = command;
    }
}