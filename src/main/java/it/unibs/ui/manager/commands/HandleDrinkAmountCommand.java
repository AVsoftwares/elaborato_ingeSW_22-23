package it.unibs.ui.manager.commands;

import it.unibs.core.Restaurant;
import it.unibs.ui.Command;
import it.unibs.ui.InputManager;
import it.unibs.ui.Menu;

public class HandleDrinkAmountCommand implements Command {
    private final Restaurant restaurant;

    public HandleDrinkAmountCommand(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void onSelection() {
        Menu menu = new Menu("Gestione consumo pro-capite di bevande");

        menu.addEntry("Visualizza consumo bevanda", () -> {
            var mapAvgDrink = restaurant.getAvgDrinkAmount();

            if (mapAvgDrink.isEmpty()) {
                System.out.println("La lista è vuota.");
            } else {
                System.out.println("Le bevande attualmente disponibili sono:");
                mapAvgDrink.keySet().forEach(System.out::println);
            }
        });
        menu.addEntry("Aggiungi nuovo", () -> {
            var mapAvgDrink = restaurant.getAvgDrinkAmount();

            var name = InputManager.readString("Inserisci il nome della bevanda: ");

            if (mapAvgDrink.containsKey(name)) {
                System.out.println("La bevanda è già presente nell'elenco.");
            } else {
                var avgAmount = InputManager.readInt("Inserisci il consumo pro-capite: ");
                mapAvgDrink.put(name, avgAmount);
            }
        });

        menu.run();
    }
}
