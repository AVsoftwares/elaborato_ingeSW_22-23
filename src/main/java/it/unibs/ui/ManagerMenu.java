package it.unibs.ui;

import it.unibs.core.Menu;
import it.unibs.core.Restaurant;
import lombok.RequiredArgsConstructor;

import java.util.*;

@RequiredArgsConstructor
public class ManagerMenu extends BaseMenu {
    private final String title;
    private final boolean isMainMenu;
    private final List<BaseMenuEntry> entries = new ArrayList<>();
    private static final String SEPARATOR = "=";
    private static final String EXIT_ENTRY = "0\tExit";
    private static final String BACK_ENTRY = "0\tBack";
    private static final String INPUT_STRING = "Enter choice: ";

    private static final String MSG_WORKLOAD = "Gestisci carico di lavoro per persona.";
    private static final String MSG_SEATS = "Gestisci numero dei posti a sedere.";
    private static final String MSG_DRINKS = "Gestisci insieme delle bevande.";
    private static final String MSG_EXTRA = "Gestisci insieme di generi alimentari extra.";
    private static final String MSG_DRINK_AMOUNT = "Gestisci consumo pro-capite di bevande.";
    private static final String MSG_EXTRA_AMOUNT = "Gestisci consumo pro-capite di generi alimentari extra.";
    private static final String MSG_DISH_RECIPE = "Gestisci corrispondenze piatto-ricetta.";
    private static final String MSG_DISH = "Gestisci denominazione e periodo di validità di ciascun piatto.";
    private static final String MSG_MAKE_RECIPE = "Crea una ricetta.";
    private static final String MSG_VIEW_RECIPE = "Visualizza le ricette disponibili.";
    private static final String MSG_MAKE_THEMATIC_MENU = "Crea un menu tematico.";
    private static final String MSG_VIEW_THEMATIC_MENU = "Visualizza i menu tematici disponibili.";


    public ManagerMenu(String title) {
        this(title, false);
    }

    @Override
    public void addEntry(String desc, Command command) {
        entries.add(new BaseMenuEntry(desc, command));
    }

    @Override
    public void addEntries(BaseMenuEntry... entries) {
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

    @Override
    public void create(Restaurant restaurant){
        this.addEntry(MSG_WORKLOAD, new HandleIndividualWorkloadCommand(restaurant));
        this.addEntry(MSG_SEATS, new HandleSeatsCommand(restaurant));
        this.addEntry(MSG_DRINKS, new HandleDrinksCommand(restaurant));
        this.addEntry(MSG_EXTRA, new HandleExtraCommand(restaurant));
        this.addEntry(MSG_DRINK_AMOUNT, new HandleDrinkAmountCommand(restaurant));
        this.addEntry(MSG_EXTRA_AMOUNT, new HandleExtraAmountCommand(restaurant));
        this.addEntry(MSG_DISH_RECIPE, new HandleExtraCommand(restaurant));
        this.addEntry(MSG_DISH, new HandleExtraCommand(restaurant));
        this.addEntry(MSG_MAKE_RECIPE, new CreateRecipeCommand(restaurant));
        this.addEntry(MSG_VIEW_RECIPE, null);
        this.addEntry(MSG_MAKE_THEMATIC_MENU, null);
        this.addEntry(MSG_VIEW_THEMATIC_MENU, null);
    }

}
