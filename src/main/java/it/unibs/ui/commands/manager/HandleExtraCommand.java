package it.unibs.ui.commands.manager;

import it.unibs.core.Restaurant;
import it.unibs.ui.Menu;

import java.util.Scanner;

public class HandleExtraCommand implements Command {

    private final Restaurant restaurant;

    public HandleExtraCommand(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void onSelection() {
        Menu menu = new Menu("Gestione generi alimentari extra");

        menu.addEntry("Visualizza lista", () -> {
            var mapAvgExtra = restaurant.getAvgExtraAmount();

            if (mapAvgExtra.isEmpty()) {
                System.out.println("La lista è vuota.");
            } else {
                System.out.println("I generi alimentari extra attualmente disponibili sono:");
                mapAvgExtra.keySet().forEach(System.out::println);
            }
        });
        menu.addEntry("Aggiungi nuovo", () -> {
            var mapAvgExtra = restaurant.getAvgExtraAmount();

            var scanner = new Scanner(System.in);

            System.out.print("Inserisci il nome del genere alimentare: ");
            var name = scanner.nextLine();

            if (mapAvgExtra.containsKey(name)) {
                System.out.println("Il genere alimentare è già presente nell'elenco.");
            } else {
                System.out.print("Inserisci il consumo pro-capite: ");
                var avgAmount = scanner.nextInt();

                mapAvgExtra.put(name, avgAmount);
            }
        });

        menu.run();
    }
}
