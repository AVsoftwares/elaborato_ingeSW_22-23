package it.unibs.ui.manager.commands;

import it.unibs.core.Restaurant;
import it.unibs.ui.Command;
import it.unibs.ui.InputManager;
import it.unibs.ui.Menu;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class DrinksCommand implements Command {
    private final Restaurant restaurant;

    @Override
    public void execute() {
        Menu menu = new Menu("Gestione bevande");

        Map<String, Float> mapAvgDrink = restaurant.getAvgDrinkAmount();

        menu.addEntry("Visualizza bevande", () -> {
            if (mapAvgDrink.isEmpty()) {
                System.out.println("La lista è vuota.");
            } else {
                System.out.println("Le bevande attualmente disponibili sono:");
                mapAvgDrink.keySet().forEach(System.out::println);
            }
        });
        menu.addEntry("Aggiungi bevanda", () -> {
            var name = InputManager.readString("Nome: ").toLowerCase();

            if (mapAvgDrink.containsKey(name)) {
                System.out.println("La bevanda è già presente nell'elenco.");
            } else {
                mapAvgDrink.put(name, null);
            }
        });

        menu.run();
    }
}
