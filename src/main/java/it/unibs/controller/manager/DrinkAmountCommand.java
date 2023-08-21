package it.unibs.controller.manager;

import it.unibs.core.Restaurant;
import it.unibs.core.unit.MeasureUnit;
import it.unibs.core.unit.MetricPrefix;
import it.unibs.core.unit.Quantity;
import it.unibs.ui.BaseMenu;
import it.unibs.ui.Command;
import it.unibs.ui.InputManager;
import it.unibs.ui.manager.ManagerView;

import java.util.Map;

public class DrinkAmountCommand implements Command {

    public static final String PER_CAPITA_DRINK_MANAGEMENT = "Gestione consumo pro-capite di bevande";
    public static final String VIEW_PER_CAPITA_BEVERAGE_CONSUMPTION = "Visualizza consumo pro-capite di bevande";
    public static final String INITIALIZE_CONSUMPTION_PER_CAPITA_BEVERAGE = "Inizializza il consumo pro-capite di bevanda";
    public static final String NAME = "Nome: ";
    public static final String PER_CAPITA_CONSUMPTION = "Consumo pro-capite: ";
    private final Restaurant restaurant = Restaurant.getInstance();
    private final ManagerView view;

    public DrinkAmountCommand(ManagerView view) {
        this.view = view;
    }

    @Override
    public void execute() {
        BaseMenu menu = new BaseMenu(PER_CAPITA_DRINK_MANAGEMENT);

        final Map<String, Quantity> averageDrinkConsumption = restaurant.getImmutableAverageDrinkConsumption();

        menu.addEntry(VIEW_PER_CAPITA_BEVERAGE_CONSUMPTION, () -> {
            final var drinkWithConsumption = restaurant.getImmutableAverageDrinkConsumptionNotNull();

            if (drinkWithConsumption.isEmpty()) {
                view.printNoDrinkAssociated();
            } else {
                view.printDrinksWithConsumption(drinkWithConsumption);
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
                    view.printDrinkPerCapita(name);
                }
            } else {
                view.printDrinkPerCapitaNotPresent(name);
            }
        });

        menu.run();
    }
}
