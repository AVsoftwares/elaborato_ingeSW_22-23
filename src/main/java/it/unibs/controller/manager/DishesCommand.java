package it.unibs.controller.manager;

import it.unibs.core.Dish;
import it.unibs.core.Period;
import it.unibs.core.Restaurant;
import it.unibs.ui.BaseMenu;
import it.unibs.ui.Command;
import it.unibs.ui.InputManager;
import it.unibs.ui.manager.ManagerView;

import java.time.LocalDate;
import java.util.Set;

public class DishesCommand implements Command {

    public static final String DISHES_MANAGEMENT = "Gestione piatti";
    public static final String ADD_DISH = "Aggiungi piatti";
    public static final String NAME = "Nome: ";
    public static final String START_VALIDITY = "Data di inizio validità: ";
    public static final String STOP_VALIDITY = "Data di fine validità: ";
    public static final String ANOTHER_DISH_Y_OR_N = "Vuoi inserire un altro piatto?  (y)es/(n)o: ";
    public static final String VIEW_DISHES = "Visualizza piatti";

    private final Restaurant restaurant = Restaurant.getInstance();

    private final ManagerView view;

    public DishesCommand(ManagerView view) {
        this.view = view;
    }

    @Override
    public void execute() {
        Set<Dish> dishes = restaurant.getDishes();

        BaseMenu menu = new BaseMenu(DISHES_MANAGEMENT);
        menu.addEntry(ADD_DISH, () -> {
            if (!dishes.isEmpty()) {
                view.printDishListInitialized();
                return;
            }

            do {
                final String name = InputManager.readString(NAME);
                final LocalDate startDate = InputManager.readDate(START_VALIDITY,
                        InputManager.DEFAULT_DATE_FORMATTER_PATTERN);
                final LocalDate expireDate = InputManager.readDate(STOP_VALIDITY,
                        InputManager.DEFAULT_DATE_FORMATTER_PATTERN);

                try {
                    Period period = new Period(startDate, expireDate);

                    if (!dishes.add(new Dish(name, period))) {
                        view.printDishPresent();
                    }
                } catch (IllegalArgumentException e) {
                    view.printPeriodNotValid();
                }
            } while (InputManager.readYesOrNo(ANOTHER_DISH_Y_OR_N));
        });
        menu.addEntry(VIEW_DISHES, () -> {
            if (dishes.isEmpty()) {
                view.printNoDishesSaved();
            } else {
                view.printDishes(dishes);
            }
        });
        menu.run();
    }
}
