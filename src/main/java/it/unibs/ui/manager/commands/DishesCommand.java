package it.unibs.ui.manager.commands;

import it.unibs.core.Dish;
import it.unibs.core.Period;
import it.unibs.core.Restaurant;
import it.unibs.ui.BaseMenu;
import it.unibs.ui.Command;
import it.unibs.ui.InputManager;

import java.time.LocalDate;
import java.util.Set;

public class DishesCommand implements Command {

    public static final String DISHES_MANAGEMENT = "Gestione piatti";
    public static final String ADD_DISH = "Aggiungi piatti";
    public static final String DISH_LIST_INITIALIZED = "La lista di piatti è già stata inizializzata.";
    public static final String NAME = "Nome: ";
    public static final String START_VALIDITY = "Data di inizio validità: ";
    public static final String STOP_VALIDITY = "Data di fine validità: ";
    public static final String DISH_ALREADY_PRESENT = "Un piatto omonimo è già presente.";
    public static final String PERIOD_NOT_VALID = "Il periodo inserito non è valido.";
    public static final String ANOTHER_DISH_Y_OR_N = "Vuoi inserire un altro piatto?  (y)es/(n)o: ";
    public static final String VIEW_DISHES = "Visualizza piatti";
    public static final String NO_DISHES_SAVED = "Non sono memorizzati piatti.";

    private final Restaurant restaurant = Restaurant.getInstance();

    @Override
    public void execute() {
        Set<Dish> dishes = restaurant.getDishes();

        BaseMenu menu = new BaseMenu(DISHES_MANAGEMENT);
        menu.addEntry(ADD_DISH, () -> {
            if (!dishes.isEmpty()) {
                System.out.println(DISH_LIST_INITIALIZED);
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
                        System.out.println(DISH_ALREADY_PRESENT);
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println(PERIOD_NOT_VALID);
                }
            } while (InputManager.readYesOrNo(ANOTHER_DISH_Y_OR_N));
        });
        menu.addEntry(VIEW_DISHES, () -> {
            if (dishes.isEmpty()) {
                System.out.println(NO_DISHES_SAVED);
            } else {
                dishes.forEach(System.out::println);
            }
        });
        menu.run();
    }
}
