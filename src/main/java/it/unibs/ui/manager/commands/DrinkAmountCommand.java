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

        Map<String, Float> mapAvgDrink = restaurant.getAvgDrinkAmount();

        menu.addEntry("Visualizza consumo pro-capite di bevande", () -> {
            if (mapAvgDrink.isEmpty()) {
                System.out.println("Non è presente nessuna bevanda.");
            } else {
                mapAvgDrink.forEach((key, value) -> {
                    System.out.println("- " + key + " consumo pro-capite " + value + " litri");
                });
            }
        });
        menu.addEntry("Inizializza il consumo pro-capite di bevanda", () -> {
            var name = InputManager.readString("Nome: ");

            if (mapAvgDrink.containsKey(name)) {
                mapAvgDrink.put(name, InputManager.readFloat("Consumo pro-capite: "));
            } else {
                System.out.println("Non è presente nessuna bevanda \"" + name + "\".");
            }
        });

        menu.run();
    }
}
