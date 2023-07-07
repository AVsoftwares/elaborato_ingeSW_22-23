package it.unibs.ui.manager.commands;

import it.unibs.core.Restaurant;
import it.unibs.core.unit.MeasureUnit;
import it.unibs.core.unit.MetricPrefix;
import it.unibs.core.unit.Quantity;
import it.unibs.ui.Command;
import it.unibs.ui.InputManager;
import it.unibs.ui.Menu;

import java.util.Map;

public class ExtraAmountCommand implements Command {

    private final Restaurant restaurant;

    public ExtraAmountCommand(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void execute() {
        final Menu menu = new Menu("Gestione consumo pro-capite di alimenti extra");

        final Map<String, Quantity> averageExtraConsumption = restaurant.getImmutableAverageExtraConsumption();

        menu.addEntry("Visualizza consumo pro-capite di alimenti extra", () -> {
            final var extraWithConsumption = restaurant.getImmutableAverageExtraConsumptionNotNull();

            if (extraWithConsumption.isEmpty()) {
                System.out.println("Non è presente nessun alimento extra con consumo pro-capite associato.");
            } else {
                extraWithConsumption.entrySet().forEach(System.out::println);
            }
        });
        menu.addEntry("Inizializza il consumo pro-capite di alimenti extra", () -> {
            final String name = InputManager.readString("Nome dell'alimento extra: ");

            if (averageExtraConsumption.containsKey(name)) {
                final float amount = InputManager.readFloat("Consumo pro-capite: ");
                final Quantity quantity = new Quantity(amount, MetricPrefix.HECTO, MeasureUnit.GRAMS);

                if (restaurant.isAverageExtraConsumptionNotSet(name)) {
                    restaurant.setAverageExtraConsumption(name, quantity);
                } else {
                    System.out.println("L'alimento extra " + name + " ha un consumo pro-capite associato.");
                }
            } else {
                System.out.println("L'alimento extra " + name + " non è presente nel ristorante.");
            }
        });

        menu.run();
    }
}
