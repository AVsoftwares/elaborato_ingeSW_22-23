package it.unibs.ui.manager.commands;

import java.util.Map;

import it.unibs.core.Restaurant;
import it.unibs.ui.Command;
import it.unibs.ui.InputManager;
import it.unibs.ui.Menu;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DrinkAmountCommand implements Command {
    
    private final Restaurant restaurant;

    @Override
    public void onSelection() {
        Menu menu = new Menu("Gestione consumo pro-capite di bevande");

        Map<String, Integer> mapAvgDrink = restaurant.getAvgDrinkAmount();

        menu.addEntry("Visualizza consumo bevanda", () -> {
            if (mapAvgDrink.isEmpty()) {
                System.out.println("La lista è vuota.");
            } else {
                System.out.println("Le bevande attualmente disponibili sono:");
                mapAvgDrink.keySet().forEach(System.out::println);
            }
        });
        menu.addEntry("Aggiungi nuovo", () -> {
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
