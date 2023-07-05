package it.unibs.ui.manager.commands;

import it.unibs.core.Restaurant;
import it.unibs.ui.Command;
import it.unibs.ui.InputManager;
import it.unibs.ui.Menu;

public class DrinkAmountCommand implements Command {

    private final Restaurant restaurant;

    public DrinkAmountCommand(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void execute() {
        Menu menu = new Menu("Gestione consumo pro-capite di bevande");

        menu.addEntry("Visualizza consumo pro-capite di bevande", () -> {
            final var drinkWithConsumption = restaurant.getImmutableAverageDrinkConsumptionNotNull();

            if (drinkWithConsumption.isEmpty()) {
                System.out.println("Non è presente nessuna bevanda con consumo pro-capite associato.");
            } else {
                drinkWithConsumption.entrySet().forEach(System.out::println);
            }
        });
        menu.addEntry("Inizializza il consumo pro-capite di bevanda", () -> {
            var name = InputManager.readString("Nome: ").toLowerCase();

            if (restaurant.isAverageDrinkConsumptionNotSet(name)) {
                restaurant.setAverageDrinkConsumption(name, InputManager.readFloat("Consumo pro-capite: "));
            } else {
                System.out.println("Non è presente nessuna bevanda \"" + name + "\".");
            }
        });

        menu.run();
    }
}
