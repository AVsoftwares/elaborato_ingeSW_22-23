package it.unibs.ui.commands.manager;

import it.unibs.core.Restaurant;
import it.unibs.ui.Command;
import it.unibs.ui.Menu;

import java.util.Scanner;

public class HandleDrinksCommand implements Command {
    private final Restaurant restaurant;

    public HandleDrinksCommand(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void onSelection(Scanner scanner) {
        Menu menu = new Menu("Gestione bevande", scanner);

        menu.addEntry("Visualizza lista", (s) -> {
            var mapAvgDrink = restaurant.getAvgDrinkAmount();

            if (mapAvgDrink.isEmpty()) {
                System.out.println("La lista è vuota.");
            } else {
                System.out.println("Le bevande attualmente disponibili sono:");
                mapAvgDrink.keySet().forEach(System.out::println);
            }
        });
        menu.addEntry("Aggiungi nuovo", (s) -> {
            var mapAvgDrink = restaurant.getAvgDrinkAmount();

            System.out.print("Inserisci il nome della bevanda: ");
            var name = s.nextLine();

            if (mapAvgDrink.containsKey(name)) {
                System.out.println("La bevanda è già presente nell'elenco.");
            } else {
                System.out.print("Inserisci il consumo pro-capite: ");
                var avgAmount = s.nextInt();

                mapAvgDrink.put(name, avgAmount);
            }
        });

        menu.run();
    }
}
