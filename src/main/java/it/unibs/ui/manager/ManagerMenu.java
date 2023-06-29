package it.unibs.ui.manager;

import it.unibs.core.Restaurant;
import it.unibs.ui.Menu;
import it.unibs.ui.manager.commands.*;

/**
 * Classe che rappresenta il menu relativo all'utente Gestore.
 */
public final class ManagerMenu extends Menu {

    private static final String MENU_NAME = "Manager Menu";
    private static final String MSG_WORKLOAD = "Gestisci carico di lavoro per persona.";
    private static final String MSG_SEATS = "Gestisci numero dei posti a sedere.";
    private static final String MSG_DRINKS = "Gestisci insieme delle bevande.";
    private static final String MSG_EXTRA = "Gestisci insieme di generi alimentari extra.";
    private static final String MSG_DRINK_AMOUNT = "Gestisci consumo pro-capite di bevande.";
    private static final String MSG_EXTRA_AMOUNT = "Gestisci consumo pro-capite di generi alimentari extra.";
    private static final String MSG_COUPLE_DISH_RECIPE = "Gestisci corrispondenze piatto-ricetta.";
    private static final String MSG_DISH = "Gestisci denominazione e periodo di validità di ciascun piatto.";
    private static final String MSG_RECIPES = "Gestisci le ricette disponibili.";
    private static final String MSG_THEMATIC_MENU = "Gestisci i menu tematici disponibili.";

    /**
     * @param restaurant
     */
    public ManagerMenu(Restaurant restaurant) {
        super(MENU_NAME);
        initMenuEntries(restaurant);
    }
    
    private void initMenuEntries(Restaurant restaurant) {
        addEntry(MSG_WORKLOAD, new IndividualWorkloadCommand(restaurant));
        addEntry(MSG_SEATS, new SeatsCommand(restaurant));
        addEntry(MSG_DRINKS, new DrinksCommand(restaurant));
        addEntry(MSG_EXTRA, new ExtraCommand(restaurant));
        addEntry(MSG_DRINK_AMOUNT, new DrinkAmountCommand(restaurant));
        addEntry(MSG_EXTRA_AMOUNT, new ExtraAmountCommand(restaurant));
        addEntry(MSG_COUPLE_DISH_RECIPE, new PairDishWithRecipeCommand(restaurant));
        addEntry(MSG_DISH, new DishesCommand(restaurant));
        addEntry(MSG_RECIPES, new RecipesCommand(restaurant));
        addEntry(MSG_THEMATIC_MENU, new ThematicMenuCommand(restaurant));
    }
}
