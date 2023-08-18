package it.unibs.ui;

import java.util.ArrayList;
import java.util.List;

public class BaseMenu {
    private static final String SEPARATOR = "=";
    private final boolean isMainMenu;
    private final List<BaseMenuEntry> entries = new ArrayList<>();
    private String title;

    public BaseMenu(String title, boolean isMainMenu) {
        this.title = title;
        this.isMainMenu = isMainMenu;
    }

    public BaseMenu() {
        this("", false);
    }

    public BaseMenu(String title) {
        this(title, false);
    }

    public void addEntry(String desc, Command command) {
        addEntry(new BaseMenuEntry(desc, command));
    }

    public void addEntry(BaseMenuEntry entry) {
        entries.add(entry);
    }

    public void run() {
        while (true) {
            print();
            int choice = selectEntry();

            if (choice == 0) {
                return;
            }

            entries.get(choice - 1).getCommand().execute();
        }
    }

    private void print() {
        final var header = SEPARATOR.repeat(5) + title + SEPARATOR.repeat(5);
        final var footer = SEPARATOR.repeat(header.length());

        System.out.println();
        System.out.println(header);

        System.out.println(isMainMenu ? "0\tExit" : "0\tBack");
        for (int i = 1; i < entries.size() + 1; i++) {
            System.out.println(i + "\t" + entries.get(i - 1).getDescription());
        }

        System.out.println(footer);
    }

    public void print(String message) {
        System.out.print(message);
    }

    private int selectEntry() {
        return InputManager.readInt("Enter choice: ", 0, entries.size());
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
