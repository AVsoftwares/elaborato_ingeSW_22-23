package it.unibs.ui.manager.commands;

import it.unibs.core.Restaurant;
import it.unibs.core.unit.Quantity;
import it.unibs.ui.BaseMenu;
import it.unibs.ui.Command;
import it.unibs.ui.InputManager;
import it.unibs.ui.manager.ManagerView;

import java.util.Map;

public class ExtraCommand implements Command {

    public static final String MSG_EXTRA_FOOD_MANAGEMENT = "Gestione generi alimentari extra";
    public static final String VIEW_EXTRA_FOOD = "Visualizza generi alimentari extra";
    public static final String ADD_EXTRA_FOOD = "Aggiungi genere alimentare";
    private final Restaurant restaurant = Restaurant.getInstance();
    private final ManagerView view;

    public ExtraCommand(ManagerView view) {
        this.view = view;
    }

    @Override
    public void execute() {
        BaseMenu menu = new BaseMenu(MSG_EXTRA_FOOD_MANAGEMENT);
        final Map<String, Quantity> averageExtraConsumption = restaurant.getImmutableAverageExtraConsumption();

        menu.addEntry(VIEW_EXTRA_FOOD, () -> {
            if (averageExtraConsumption.isEmpty()) {
                view.printEmptyList();
            } else {
                view.printExtraAvailable();
                averageExtraConsumption.keySet().forEach(System.out::println);
            }
        });
        menu.addEntry(ADD_EXTRA_FOOD, () -> {
            var name = InputManager.readString("Nome: ").toLowerCase();

            if (averageExtraConsumption.containsKey(name)) {
                view.printExtraPresent();
            } else {
                restaurant.addExtra(name);
            }
        });

        menu.run();
    }
}
