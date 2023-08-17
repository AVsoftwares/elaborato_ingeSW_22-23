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

    public static final String PER_CAPITA_DRINK_MANAGEMENT = "Gestione consumo pro-capite di bevande";
    public static final String VIEW_PER_CAPITA_BEVERAGE_CONSUMPTION = "Visualizza consumo pro-capite di bevande";
    public static final String NO_DRINKS_WITH_CONSUMPTION_PER_CAPITA_ASSOCIATED = "Non è presente nessuna bevanda con consumo pro-capite associato.";
    public static final String INITIALIZE_CONSUMPTION_PER_CAPITA_BEVERAGE = "Inizializza il consumo pro-capite di bevanda";
    public static final String NAME = "Nome: ";
    public static final String PER_CAPITA_CONSUMPTION = "Consumo pro-capite: ";
    public static final String DRINK_MSG = "La bevanda ";
    public static final String ASSOCIATED_CONSUMPTION_PER_CAPITA = " ha un consumo pro-capite associato.";
    public static final String NOT_PRESENT_MSG = " non è presente nel ristorante.";
    private final Restaurant restaurant = Restaurant.getInstance();

    @Override
    public void execute() {
        Menu menu = new Menu(PER_CAPITA_DRINK_MANAGEMENT);

        final Map<String, Quantity> averageDrinkConsumption = restaurant.getImmutableAverageDrinkConsumption();

        menu.addEntry(VIEW_PER_CAPITA_BEVERAGE_CONSUMPTION, () -> {
            final var drinkWithConsumption = restaurant.getImmutableAverageDrinkConsumptionNotNull();

            if (drinkWithConsumption.isEmpty()) {
                System.out.println(NO_DRINKS_WITH_CONSUMPTION_PER_CAPITA_ASSOCIATED);
            } else {
                drinkWithConsumption.entrySet().forEach(System.out::println);
            }
        });
        menu.addEntry(INITIALIZE_CONSUMPTION_PER_CAPITA_BEVERAGE, () -> {
            var name = InputManager.readString(NAME).toLowerCase();

            if (averageDrinkConsumption.containsKey(name)) {
                if (restaurant.isAverageDrinkConsumptionNotSet(name)) {
                    final float amount = InputManager.readFloat(PER_CAPITA_CONSUMPTION);
                    final Quantity quantity = new Quantity(amount, MetricPrefix.NONE, MeasureUnit.LITERS);
                    restaurant.setAverageDrinkConsumption(name, quantity);
                } else {
                    System.out.println(DRINK_MSG + name + ASSOCIATED_CONSUMPTION_PER_CAPITA);
                }
            } else {
                System.out.println(DRINK_MSG + name + NOT_PRESENT_MSG);
            }
        });

        menu.run();
    }
}
