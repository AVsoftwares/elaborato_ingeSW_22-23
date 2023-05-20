package it.unibs.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.RequiredArgsConstructor;

/**
 * Classe usata per l'istanziazione di menu.
 */
@RequiredArgsConstructor
public class Menu {
    private final String title;
    private final boolean isMainMenu;
    private final List<MenuEntry> entries = new ArrayList<>();
    private static final String SEPARATOR = "=";
    private static final String EXIT_ENTRY = "0\tExit";
    private static final String BACK_ENTRY = "0\tBack";
    private static final String INPUT_STRING = "Enter choice: ";

    /**
     * @param title titolo da visualizzare del menu
     */
    public Menu(String title) {
        this(title, false);
    }


    /**
     * @param desc breve descrizione testuale del comando associato alla entry
     * @param command comando associato alla entry
     * @see command
     */
    public void addEntry(String desc, Command command) {
        entries.add(new MenuEntry(desc, command));
    }


    /**
     * @param entries lista di entries da aggiungere
     */
    public void addEntries(MenuEntry... entries) {
        this.entries.addAll(Arrays.asList(entries));
    }

    /**
     * Metodo di visualizzazione del menu all'utente ed ottenimento della relativa scelta
     *
     */
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
}
