package it.unibs.ui.manager.commands;

import it.unibs.core.Restaurant;
import it.unibs.ui.Command;
import it.unibs.ui.InputManager;
import it.unibs.ui.Menu;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class DrinkAmountCommand implements Command {

    private final Restaurant restaurant;

    @Override
    public void execute() {
        Menu menu = new Menu("Gestione consumo pro-capite di bevande");

        Map<String, Float> mapAvgDrink = restaurant.getAvgDrinkAmount();

        menu.addEntry("Visualizza consumo pro-capite di bevande", () -> {
            final var drinkWithConsumption = mapAvgDrink.entrySet().stream()
                    .filter(e -> e.getValue() != null)
                    .toList();

            if (drinkWithConsumption.isEmpty()) {
                System.out.println("Non è presente nessuna bevanda con consumo pro-capite associato.");
            } else {
                drinkWithConsumption.forEach(System.out::println);
            }
        });
        menu.addEntry("Inizializza il consumo pro-capite di bevanda", () -> {
            var name = InputManager.readString("Nome: ").toLowerCase();

            if (mapAvgDrink.containsKey(name)) {
                mapAvgDrink.put(name, InputManager.readFloat("Consumo pro-capite: "));
            } else {
                System.out.println("Non è presente nessuna bevanda \"" + name + "\".");
            }
        });

        menu.run();
    }
}
