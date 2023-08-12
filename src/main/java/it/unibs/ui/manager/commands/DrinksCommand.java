package it.unibs.ui.manager.commands;

import it.unibs.core.Restaurant;
import it.unibs.core.unit.Quantity;
import it.unibs.ui.Command;
import it.unibs.ui.InputManager;
import it.unibs.ui.Menu;

import java.util.Map;

public class DrinksCommand implements Command {
    public static final String DRINKS_MANAGEMENT = "Gestione bevande";
    public static final String VIEW_DRINKS = "Visualizza bevande";
    public static final String MSG_EMPTY_LIST = "La lista è vuota.";
    public static final String MSG_DRINKS_AVAILABLE_NOW = "Le bevande attualmente disponibili sono:";
    public static final String ADD_BEVERAGE = "Aggiungi bevanda";
    public static final String BEVERAGE_ALREADY_PRESENT = "La bevanda è già presente nell'elenco.";
    private final Restaurant restaurant;

    public DrinksCommand() {
        this.restaurant = Restaurant.getInstance();
    }

    @Override
    public void execute() {
        Menu menu = new Menu(DRINKS_MANAGEMENT);

        final Map<String, Quantity> averageDrinkConsumption = restaurant.getImmutableAverageDrinkConsumption();

        menu.addEntry(VIEW_DRINKS, () -> {
            if (averageDrinkConsumption.isEmpty()) {
                System.out.println(MSG_EMPTY_LIST);
            } else {
                System.out.println(MSG_DRINKS_AVAILABLE_NOW);
                averageDrinkConsumption.keySet().forEach(System.out::println);
            }
        });
        menu.addEntry(ADD_BEVERAGE, () -> {
            var name = InputManager.readString("Nome: ").toLowerCase();

            if (averageDrinkConsumption.containsKey(name)) {
                System.out.println(BEVERAGE_ALREADY_PRESENT);
            } else {
                restaurant.addDrink(name);
            }
        });

        menu.run();
    }
}
