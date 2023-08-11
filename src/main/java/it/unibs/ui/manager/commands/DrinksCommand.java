package it.unibs.ui.manager.commands;

import it.unibs.core.Restaurant;
import it.unibs.core.unit.Quantity;
import it.unibs.ui.Command;
import it.unibs.ui.InputManager;
import it.unibs.ui.Menu;

import java.util.Map;

public class DrinksCommand implements Command {
    private final Restaurant restaurant;

    public DrinksCommand() {
        this.restaurant = Restaurant.getInstance();
    }

    @Override
    public void execute() {
        Menu menu = new Menu("Gestione bevande");

        final Map<String, Quantity> averageDrinkConsumption = restaurant.getImmutableAverageDrinkConsumption();

        menu.addEntry("Visualizza bevande", () -> {
            if (averageDrinkConsumption.isEmpty()) {
                System.out.println("La lista è vuota.");
            } else {
                System.out.println("Le bevande attualmente disponibili sono:");
                averageDrinkConsumption.keySet().forEach(System.out::println);
            }
        });
        menu.addEntry("Aggiungi bevanda", () -> {
            var name = InputManager.readString("Nome: ").toLowerCase();

            if (averageDrinkConsumption.containsKey(name)) {
                System.out.println("La bevanda è già presente nell'elenco.");
            } else {
                restaurant.addDrink(name);
            }
        });

        menu.run();
    }
}
