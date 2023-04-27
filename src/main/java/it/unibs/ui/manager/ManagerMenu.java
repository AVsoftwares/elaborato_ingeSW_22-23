package it.unibs.ui.manager;

import it.unibs.core.Restaurant;
import it.unibs.ui.Menu;
import it.unibs.ui.manager.commands.CreateRecipeCommand;
import it.unibs.ui.manager.commands.CreateThematicMenuCommand;
import it.unibs.ui.manager.commands.HandleDishesCommand;
import it.unibs.ui.manager.commands.HandleDrinkAmountCommand;
import it.unibs.ui.manager.commands.HandleDrinksCommand;
import it.unibs.ui.manager.commands.HandleExtraAmountCommand;
import it.unibs.ui.manager.commands.HandleExtraCommand;
import it.unibs.ui.manager.commands.HandleIndividualWorkloadCommand;
import it.unibs.ui.manager.commands.HandleDishesRecipesCommand;
import it.unibs.ui.manager.commands.HandleSeatsCommand;
import it.unibs.ui.manager.commands.ViewRecipesCommand;
import it.unibs.ui.manager.commands.ViewThematicMenuCommand;

public final class ManagerMenu extends Menu {
    private final Restaurant restaurant;

    private static final String MENU_NAME = "Manager Menu";
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
    
    public ManagerMenu(Restaurant restaurant) {
        super(MENU_NAME, false);
        this.restaurant = restaurant;
        initMenuEntries();
    }
    
    private void initMenuEntries() {
        addEntry(MSG_WORKLOAD, new HandleIndividualWorkloadCommand(restaurant));
        addEntry(MSG_SEATS, new HandleSeatsCommand(restaurant));
        addEntry(MSG_DRINKS, new HandleDrinksCommand(restaurant));
        addEntry(MSG_EXTRA, new HandleExtraCommand(restaurant));
        addEntry(MSG_DRINK_AMOUNT, new HandleDrinkAmountCommand(restaurant));
        addEntry(MSG_EXTRA_AMOUNT, new HandleExtraAmountCommand(restaurant));
        addEntry(MSG_DISH_RECIPE, new HandleDishesRecipesCommand(restaurant));
        addEntry(MSG_DISH, new HandleDishesCommand(restaurant));
        addEntry(MSG_MAKE_RECIPE, new CreateRecipeCommand(restaurant));
        addEntry(MSG_VIEW_RECIPE, new ViewRecipesCommand(restaurant));
        addEntry(MSG_MAKE_THEMATIC_MENU, new CreateThematicMenuCommand(restaurant));
        addEntry(MSG_VIEW_THEMATIC_MENU, new ViewThematicMenuCommand(restaurant));
    }
}
