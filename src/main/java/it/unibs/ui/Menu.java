package it.unibs.ui;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private static final String SEPARATOR = "=";
    private static final String EXIT_ENTRY = "0\tExit";
    private static final String BACK_ENTRY = "0\tBack";
    private static final String INPUT_STRING = "Enter choice: ";
    private final boolean isMainMenu;
    private final List<MenuEntry> entries = new ArrayList<>();
    private String title;

    public Menu(String title, boolean isMainMenu) {
        this.title = title;
        this.isMainMenu = isMainMenu;
    }

    public Menu() {
        this("", false);
    }

    public Menu(String title) {
        this(title, false);
    }

    public void addEntry(String desc, Command command) {
        addEntry(new MenuEntry(desc, command));
    }

    public void addEntry(MenuEntry entry) {
        entries.add(entry);
    }

    public void run() {
        while (true) {
            print();
            int choice = selectEntry();

            if (choice == 0) {
                return;
            }

            entries.get(choice - 1).getCommand().onSelection();
        }
    }

    private void print() {
        final var header = SEPARATOR.repeat(5) + title + SEPARATOR.repeat(5);
        final var footer = SEPARATOR.repeat(header.length());

        System.out.println(header);

        System.out.println(isMainMenu ? EXIT_ENTRY : BACK_ENTRY);
        for (int i = 1; i < entries.size() + 1; i++) {
            System.out.println(i + "\t" + entries.get(i - 1).getDescription());
        }

        System.out.println(footer);
    }

    private int selectEntry() {
        return InputManager.readInt(INPUT_STRING, 0, entries.size());
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
