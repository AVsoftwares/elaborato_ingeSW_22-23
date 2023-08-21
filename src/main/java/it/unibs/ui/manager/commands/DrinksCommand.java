package it.unibs.ui.manager.commands;

import it.unibs.core.Restaurant;
import it.unibs.core.unit.Quantity;
import it.unibs.ui.BaseMenu;
import it.unibs.ui.Command;
import it.unibs.ui.InputManager;
import it.unibs.ui.manager.ManagerView;

import java.util.Map;

public class DrinksCommand implements Command {
    public static final String DRINKS_MANAGEMENT = "Gestione bevande";
    public static final String VIEW_DRINKS = "Visualizza bevande";
    public static final String ADD_BEVERAGE = "Aggiungi bevanda";
    private final Restaurant restaurant = Restaurant.getInstance();
    private final ManagerView view;

    public DrinksCommand(ManagerView view) {
        this.view = view;
    }

    @Override
    public void execute() {
        BaseMenu menu = new BaseMenu(DRINKS_MANAGEMENT);

        final Map<String, Quantity> averageDrinkConsumption = restaurant.getImmutableAverageDrinkConsumption();

        menu.addEntry(VIEW_DRINKS, () -> {
            if (averageDrinkConsumption.isEmpty()) {
                view.printEmptyList();
            } else {
                view.printDrinksAvailable(averageDrinkConsumption.keySet().stream().toList());
            }
        });
        menu.addEntry(ADD_BEVERAGE, () -> {
            var name = InputManager.readString("Nome: ").toLowerCase();

            if (averageDrinkConsumption.containsKey(name)) {
                view.printDrinkPresent();
            } else {
                restaurant.addDrink(name);
            }
        });

        menu.run();
    }
}
