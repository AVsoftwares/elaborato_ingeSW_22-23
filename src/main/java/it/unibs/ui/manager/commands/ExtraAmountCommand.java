package it.unibs.ui.manager.commands;

import it.unibs.core.Restaurant;
import it.unibs.core.unit.MeasureUnit;
import it.unibs.core.unit.MetricPrefix;
import it.unibs.core.unit.Quantity;
import it.unibs.ui.BaseMenu;
import it.unibs.ui.Command;
import it.unibs.ui.InputManager;
import it.unibs.ui.manager.ManagerView;

import java.util.Map;

public class ExtraAmountCommand implements Command {

    public static final String EXTRA_FOOD_MANAGEMENT_PER_CAPITA = "Gestione consumo pro-capite di alimenti extra";
    public static final String VIEW_EXTRA_FOOD_CONSUMPTION_PER_CAPITA = "Visualizza consumo pro-capite di alimenti extra";
    public static final String INITIALIZE_CONSUMPTION_EXTRA_FOOD_PER_PERSON = "Inizializza il consumo pro-capite di alimenti extra";
    public static final String PRO_CAPITE = "Consumo pro-capite: ";
    private final Restaurant restaurant = Restaurant.getInstance();
    private final ManagerView view;

    public ExtraAmountCommand(ManagerView view) {
        this.view = view;
    }


    @Override
    public void execute() {
        final BaseMenu menu = new BaseMenu(EXTRA_FOOD_MANAGEMENT_PER_CAPITA);

        final Map<String, Quantity> averageExtraConsumption = restaurant.getImmutableAverageExtraConsumption();

        menu.addEntry(VIEW_EXTRA_FOOD_CONSUMPTION_PER_CAPITA, () -> {
            final var extraWithConsumption = restaurant.getImmutableAverageExtraConsumptionNotNull();

            if (extraWithConsumption.isEmpty()) {
                view.printNoExtraFood();
            } else {
                extraWithConsumption.entrySet().forEach(System.out::println);
            }
        });
        menu.addEntry(INITIALIZE_CONSUMPTION_EXTRA_FOOD_PER_PERSON, () -> {
            final String name = InputManager.readString("Nome dell'alimento extra: ");

            if (averageExtraConsumption.containsKey(name)) {
                final float amount = InputManager.readFloat(PRO_CAPITE);
                final Quantity quantity = new Quantity(amount, MetricPrefix.HECTO, MeasureUnit.GRAMS);

                if (restaurant.isAverageExtraConsumptionNotSet(name)) {
                    restaurant.setAverageExtraConsumption(name, quantity);
                } else {
                    view.printExtraConsumption(name);
                }
            } else {
                view.printExtraNotPresent(name);
            }
        });

        menu.run();
    }
}
