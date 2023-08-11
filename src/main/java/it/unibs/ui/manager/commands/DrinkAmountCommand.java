package it.unibs.ui.manager.commands;

import it.unibs.core.Restaurant;
import it.unibs.core.unit.MeasureUnit;
import it.unibs.core.unit.MetricPrefix;
import it.unibs.core.unit.Quantity;
import it.unibs.ui.Command;
import it.unibs.ui.InputManager;
import it.unibs.ui.Menu;

import java.util.Map;

public class DrinkAmountCommand implements Command {

    private final Restaurant restaurant;

    public DrinkAmountCommand() {
        this.restaurant = Restaurant.getInstance();
    }

    @Override
    public void execute() {
        Menu menu = new Menu("Gestione consumo pro-capite di bevande");

        final Map<String, Quantity> averageDrinkConsumption = restaurant.getImmutableAverageDrinkConsumption();

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

            if (averageDrinkConsumption.containsKey(name)) {
                if (restaurant.isAverageDrinkConsumptionNotSet(name)) {
                    final float amount = InputManager.readFloat("Consumo pro-capite: ");
                    final Quantity quantity = new Quantity(amount, MetricPrefix.NONE, MeasureUnit.LITERS);
                    restaurant.setAverageDrinkConsumption(name, quantity);
                } else {
                    System.out.println("La bevanda " + name + " ha un consumo pro-capite associato.");
                }
            } else {
                System.out.println("La bevanda " + name + " non è presente nel ristorante.");
            }
        });

        menu.run();
    }
}
