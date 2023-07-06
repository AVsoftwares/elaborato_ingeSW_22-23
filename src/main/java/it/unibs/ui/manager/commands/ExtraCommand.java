package it.unibs.ui.manager.commands;

import it.unibs.core.Restaurant;
import it.unibs.core.unit.Quantity;
import it.unibs.ui.Command;
import it.unibs.ui.InputManager;
import it.unibs.ui.Menu;

import java.util.Map;

public class ExtraCommand implements Command {

    private final Restaurant restaurant;

    public ExtraCommand(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void execute() {
        Menu menu = new Menu("Gestione generi alimentari extra");
        final Map<String, Quantity> averageExtraConsumption = restaurant.getImmutableAverageExtraConsumption();

        menu.addEntry("Visualizza generi alimentari extra", () -> {
            if (averageExtraConsumption.isEmpty()) {
                System.out.println("La lista è vuota.");
            } else {
                System.out.println("I generi alimentari extra attualmente disponibili sono:");
                averageExtraConsumption.keySet().forEach(System.out::println);
            }
        });
        menu.addEntry("Aggiungi genere alimentare", () -> {
            var name = InputManager.readString("Nome: ").toLowerCase();

            if (averageExtraConsumption.containsKey(name)) {
                System.out.println("Il genere alimentare è già presente nell'elenco.");
            } else {
                restaurant.setAverageExtraConsumption(name, null);
            }
        });

        menu.run();
    }
}
