package it.unibs.ui.manager;

import it.unibs.core.Dish;
import it.unibs.ui.BaseMenu;
import it.unibs.ui.manager.commands.*;

import java.util.Set;

/**
 * Classe che rappresenta il menu relativo all'utente Gestore.
 */
public final class ManagerView extends BaseMenu {

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
    public static final String DISH_LIST_INITIALIZED = "La lista di piatti è già stata inizializzata.";
    public static final String DISH_ALREADY_PRESENT = "Un piatto omonimo è già presente.";
    public static final String PERIOD_NOT_VALID = "Il periodo inserito non è valido.";
    public static final String NO_DISHES_SAVED = "Non sono memorizzati piatti.";

    public ManagerView() {
        super(MENU_NAME);
        addEntry(MSG_WORKLOAD, new IndividualWorkloadCommand());
        addEntry(MSG_SEATS, new SeatsCommand());
        addEntry(MSG_DRINKS, new DrinksCommand());
        addEntry(MSG_EXTRA, new ExtraCommand());
        addEntry(MSG_DRINK_AMOUNT, new DrinkAmountCommand());
        addEntry(MSG_EXTRA_AMOUNT, new ExtraAmountCommand());
        addEntry(MSG_COUPLE_DISH_RECIPE, new PairDishWithRecipeCommand());
        addEntry(MSG_DISH, new DishesCommand(this));
        addEntry(MSG_RECIPES, new RecipesCommand());
        addEntry(MSG_THEMATIC_MENU, new ThematicMenuCommand());
    }

    public void printDishListInitialized() {
        System.out.println(DISH_LIST_INITIALIZED);
    }
    public void printDishPresent() {
        System.out.println(DISH_ALREADY_PRESENT);
    }
    public void printPeriodNotValid() {
        System.out.println(PERIOD_NOT_VALID);
    }
    public void printNoDishesSaved() {
        System.out.println(NO_DISHES_SAVED);
    }
    public void printDishes(Set<Dish> dishes){
        dishes.forEach(System.out::println);
    }
}
