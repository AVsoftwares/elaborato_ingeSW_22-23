package it.unibs.ui;

import it.unibs.ui.commands.manager.Command;
import lombok.RequiredArgsConstructor;

import java.util.*;

@RequiredArgsConstructor
public class Menu {
    private final String title;
    private final boolean isMainMenu;
    private final List<MenuEntry> entries = new ArrayList<>();
    private static final String SEPARATOR = "=";
    private static final String EXIT_ENTRY = "0\tExit";
    private static final String BACK_ENTRY = "0\tBack";
    private static final String INPUT_STRING = "Enter choice: ";
    private static final String ERROR_INVALID_INPUT = "ERROR: invalid input.";
    private static final String ERROR_INPUT_OUT_OF_RANGE = "ERROR: input out of range.";

    public Menu(String title) {
        this(title, false);
    }

    public void addEntry(String desc, Command command) {
        entries.add(new MenuEntry(desc, command));
    }

    public void addEntries(MenuEntry... entries) {
        this.entries.addAll(Arrays.asList(entries));
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
        int choice;

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print(INPUT_STRING);

            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println(ERROR_INVALID_INPUT);
                scanner.nextLine();
                continue;
            }

            if (choice < 0 || choice > entries.size()) {
                scanner.nextLine();
                System.out.println(ERROR_INPUT_OUT_OF_RANGE);
                continue;
            }

            return choice;
        }
    }
}
