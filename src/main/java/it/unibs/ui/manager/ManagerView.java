package it.unibs.ui.manager;

import it.unibs.controller.manager.*;
import it.unibs.core.Dish;
import it.unibs.core.Period;
import it.unibs.core.Recipe;
import it.unibs.core.ThematicMenu;
import it.unibs.core.unit.Quantity;
import it.unibs.ui.BaseMenu;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Classe che rappresenta il menu relativo all'utente Gestore.
 */
public final class ManagerView extends BaseMenu {

    private static final String DISH_LIST_INITIALIZED = "La lista di piatti è già stata inizializzata.";
    private static final String DISH_ALREADY_PRESENT = "Un piatto omonimo è già presente.";
    private static final String PERIOD_NOT_VALID = "Il periodo inserito non è valido.";
    private static final String NO_DISHES_SAVED = "Non sono memorizzati piatti.";
    private static final String NO_DRINKS_WITH_CONSUMPTION_PER_CAPITA_ASSOCIATED = "Non è presente nessuna bevanda con consumo pro-capite associato.";
    private static final String DRINK_MSG = "La bevanda ";
    private static final String ASSOCIATED_CONSUMPTION_PER_CAPITA = " ha un consumo pro-capite associato.";
    private static final String NOT_PRESENT_MSG = " non è presente nel ristorante.";
    private static final String MSG_DRINKS_AVAILABLE_NOW = "Le bevande attualmente disponibili sono:";
    private static final String BEVERAGE_ALREADY_PRESENT = "La bevanda è già presente nell'elenco.";
    private static final String MSG_EMPTY_LIST = "La lista è vuota.";
    private static final String NO_EXTRA_FOOD_CONSUMPTION_PER_CAPITA = "Non è presente nessun alimento extra con consumo pro-capite associato.";
    private static final String EXTRA_MSG = "L'alimento extra ";
    private static final String EXTRA_FOOD_CONSUMPTION_PER_CAPITA = " ha un consumo pro-capite associato.";
    private static final String NOT_PRESENT_RESTAURANT = " non è presente nel ristorante.";
    private static final String CURRENTLY_AVAILABLE = "I generi alimentari extra attualmente disponibili sono:";
    private static final String ALREADY_PRESENT = "Il genere alimentare è già presente nell'elenco.";
    private static final String VALUE_NOT_INITIALIZED = "Il valore non è ancora stato inizializzato.";
    private static final String CURRENT_VALUE = "Il valore attuale è: ";
    private static final String ALL_DISHES_PAIRED = "Tutti i piatti sono associati ad una ricetta.";
    private static final String NO_PRESENT_RECIPES_IMPOSSIBLE_PROCEED = "Non sono presenti ricette, impossibile procedere.";
    private static final String DISHES_NOT_PAIRED = "I seguenti piatti non hanno una ricetta associata: ";
    private static final String DISH_NOT_IN_LIST = "Il piatto non è presente nella lista.";
    private static final String DISH_ALREADY_PAIRED = "Il piatto ha già una ricetta associata";
    private static final String MSG_SAVED_RECIPES = "Sono memorizzate le seguenti ricette: ";
    private static final String NO_RECIPES_SAVED = "Non sono memorizzate ricette.";
    private static final String AMOUNT_NOT_VALID = """
            La quantità inserita non è valida.
            Deve essere nel formato: quantity [prefix unit]
            Le unità di misura accettate sono (l)itri e (g)rammi, se omessa si considerano le unità""";
    private static final String THEMATIC_MENU_SAVED = "I menu tematici memorizzati sono:";
    private static final String NO_DISH_AVAILABLE = "Nessun piatto disponibile, impossibile continuare.";
    private static final String EXPIRATION_DATE_BEFORE_START_DATE_VALIDITY = "La data di scadenza inserita è precedente alla data di inizio validità, impossibile continuare.";
    private static final String NO_DISH = "Nessun piatto ";
    private static final String NO_PAIRED_RECIPE_DISH = "Il piatto non ha una ricetta associata, non è possibile inserirlo nel menu.";
    private static final String DISH_ADDED = "Piatto aggiunto al menu.";
    private static final String DISH_WORKLOAD_EXCEDED = "Il piatto scelto ha un carico di lavoro incompatibile con il menu.";
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


    public ManagerView() {
        super(MENU_NAME);
        initMenuEntries();
    }

    private void initMenuEntries() {
        addEntry(MSG_WORKLOAD, new IndividualWorkloadCommand(this));
        addEntry(MSG_SEATS, new SeatsCommand(this));
        addEntry(MSG_DRINKS, new DrinksCommand(this));
        addEntry(MSG_EXTRA, new ExtraCommand(this));
        addEntry(MSG_DRINK_AMOUNT, new DrinkAmountCommand(this));
        addEntry(MSG_EXTRA_AMOUNT, new ExtraAmountCommand(this));
        addEntry(MSG_COUPLE_DISH_RECIPE, new PairDishWithRecipeCommand(this));
        addEntry(MSG_DISH, new DishesCommand(this));
        addEntry(MSG_RECIPES, new RecipesCommand(this));
        addEntry(MSG_THEMATIC_MENU, new ThematicMenuCommand(this));
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

    public void printDishes(Set<Dish> dishes) {
        dishes.forEach(d -> System.out.println(formatDish(d)));
    }

    public void printEmptyList() {
        System.out.println(MSG_EMPTY_LIST);
    }

    public void printDrinksAvailable(List<String> drinks) {
        System.out.println(MSG_DRINKS_AVAILABLE_NOW);
        drinks.forEach(System.out::println);
    }

    public void printDrinkPresent() {
        System.out.println(BEVERAGE_ALREADY_PRESENT);
    }

    public void printDrinkPerCapita(String drinkName) {
        System.out.println(DRINK_MSG + drinkName + ASSOCIATED_CONSUMPTION_PER_CAPITA);
    }

    public void printDrinkPerCapitaNotPresent(String drinkName) {
        System.out.println(DRINK_MSG + drinkName + NOT_PRESENT_MSG);
    }

    public void printNoDrinkAssociated() {
        System.out.println(NO_DRINKS_WITH_CONSUMPTION_PER_CAPITA_ASSOCIATED);
    }

    public void printNoExtraFood() {
        System.out.println(NO_EXTRA_FOOD_CONSUMPTION_PER_CAPITA);
    }

    public void printExtraConsumption(String extraName) {
        System.out.println(EXTRA_MSG + extraName + EXTRA_FOOD_CONSUMPTION_PER_CAPITA);
    }

    public void printExtraNotPresent(String extraName) {
        System.out.println(EXTRA_MSG + extraName + NOT_PRESENT_RESTAURANT);
    }

    public void printExtraAvailable(Set<String> extras) {
        System.out.println(CURRENTLY_AVAILABLE);
        extras.forEach(System.out::println);
    }

    public void printExtraPresent() {
        System.out.println(ALREADY_PRESENT);
    }

    public void printValueNotInitialized() {
        System.out.println(VALUE_NOT_INITIALIZED);
    }

    public void printCurrentValue(int value) {
        System.out.println(CURRENT_VALUE + value);
    }

    public void printAllDishesPaired() {
        System.out.println(ALL_DISHES_PAIRED);
    }

    public void printNoRecipes() {
        System.out.println(NO_PRESENT_RECIPES_IMPOSSIBLE_PROCEED);
    }

    public void printNoPairedDishes(List<Dish> dishesWithoutRecipe) {
        System.out.println(DISHES_NOT_PAIRED);
        dishesWithoutRecipe.forEach(d -> System.out.println(formatDish(d)));
    }

    public void printDishNotPresent() {
        System.out.println(DISH_NOT_IN_LIST);
    }

    public void printDishAlreadyPaired() {
        System.out.println(DISH_ALREADY_PAIRED);
    }

    public void printSavedRecipes() {
        System.out.println(MSG_SAVED_RECIPES);
    }

    public void printRecipes(List<Recipe> recipes) {
        for (int i = 0; i < recipes.size(); i++) {
            System.out.println("\t- " + i + " " + formatRecipe(recipes.get(i)));
        }
    }

    public void printNotSavedRecipes() {
        System.out.println(NO_RECIPES_SAVED);
    }

    public void printNotValidAmount() {
        System.out.println(AMOUNT_NOT_VALID);
    }

    public void printThematicSaved(Set<ThematicMenu> thematicMenus) {
        System.out.println(THEMATIC_MENU_SAVED);
        thematicMenus.forEach(m -> System.out.println(formatMenu(m)));
    }

    public void printNoDishesAvailable() {
        System.out.println(NO_DISH_AVAILABLE);
    }

    public void printDateBeforeStart() {
        System.out.println(EXPIRATION_DATE_BEFORE_START_DATE_VALIDITY);
    }

    public void printNoDish(String dishName) {
        System.out.println(NO_DISH + dishName);
    }

    public void printNotPaired() {
        System.out.println(NO_PAIRED_RECIPE_DISH);
    }

    public void printDishAdded() {
        System.out.println(DISH_ADDED);
    }

    public void printWorkloadExceeded() {
        System.out.println(DISH_WORKLOAD_EXCEDED);
    }

    public void printExtrasWithConsumption(Map<String, Quantity> extraWithConsumption) {
        extraWithConsumption.forEach((key, value) -> System.out.println(key + " " + formatQuantity(value)));
    }

    public void printDrinksWithConsumption(Map<String, Quantity> drinkWithConsumption) {
        drinkWithConsumption.forEach((key, value) -> System.out.println(key + " " + formatQuantity(value)));
    }

    private String formatDish(Dish dish) {
        return String.join(
                " ",
                dish.getName(),
                formatPeriod(dish.getPeriod()));
    }

    private String formatQuantity(Quantity quantity) {
        return quantity.getAmount() + " " + quantity.getPrefix().getSymbol() + quantity.getUnit().getSymbol();
    }

    private String formatMenu(ThematicMenu menu) {
        return String.join(
                "",
                menu.getName(),
                formatPeriod(menu.getPeriod()));
    }

    private String formatRecipe(Recipe recipe) {
        var ingredientsString = recipe.getIngredients().keySet().stream().map(Object::toString).collect(Collectors.joining(", "));

        return "Ingredienti: " + ingredientsString + "\n\t\tPorzioni: " + recipe.getPortions()
                + "\n\t\tCarico di lavoro per porzione: " + recipe.getPortionWorkload();

    }

    private String formatPeriod(Period period) {
        return String.join("-",
                period.getStartDate().format(Period.DEFAULT_FORMATTER),
                period.getEndDate().format(Period.DEFAULT_FORMATTER));
    }
}