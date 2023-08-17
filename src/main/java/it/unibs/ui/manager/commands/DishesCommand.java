package it.unibs.ui.manager.commands;

import it.unibs.core.Dish;
import it.unibs.core.Period;
import it.unibs.core.Restaurant;
import it.unibs.ui.Command;
import it.unibs.ui.InputManager;
import it.unibs.ui.Menu;

import java.time.LocalDate;
import java.util.Set;

public class DishesCommand implements Command {

    public static final String DISHES_MANAGEMENT = "Gestione piatti";
    public static final String ADD_DISH = "Aggiungi piatti";

    public static final String NAME = "Nome: ";
    public static final String START_VALIDITY = "Data di inizio validità: ";
    public static final String STOP_VALIDITY = "Data di fine validità: ";
    public static final String ANOTHER_DISH_Y_OR_N = "Vuoi inserire un altro piatto?  (y)es/(n)o: ";
    public static final String VIEW_DIHES = "Visualizza piatti";


    private final Restaurant model = Restaurant.getInstance();
    private final DishesCommandView dishView = new DishesCommandView() ;
    @Override
    public void execute() {

        Set<Dish> dishes = model.getDishes();

        Menu menu = new Menu(DISHES_MANAGEMENT);

        menu.addEntry(ADD_DISH, () -> {
            if (!dishes.isEmpty()) {
                dishView.printDishListInitialized();
                return;
            }

            do {
                final String name = InputManager.readString(NAME);
                final LocalDate startDate = InputManager.readDate(START_VALIDITY,
                        InputManager.DEFAULT_DATE_FORMATTER_PATTERN);
                final LocalDate expireDate = InputManager.readDate(STOP_VALIDITY,
                        InputManager.DEFAULT_DATE_FORMATTER_PATTERN);

                if (startDate.isBefore(expireDate)) {
                    if (!dishes.add(new Dish(name, new Period(startDate, expireDate)))) {
                        dishView.printDishPresent();
                    }
                } else {
                    dishView.printPeriodNotValid();
                }
            } while (InputManager.readYesOrNo(ANOTHER_DISH_Y_OR_N));
        });

        menu.addEntry(VIEW_DIHES, () -> {
                dishView.printDishes(dishes);
        });
        menu.run();
    }
}
