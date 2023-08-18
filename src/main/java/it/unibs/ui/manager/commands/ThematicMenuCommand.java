package it.unibs.ui.manager.commands;

import it.unibs.core.Dish;
import it.unibs.core.Period;
import it.unibs.core.Restaurant;
import it.unibs.core.ThematicMenu;
import it.unibs.ui.BaseMenu;
import it.unibs.ui.Command;
import it.unibs.ui.InputManager;
import it.unibs.ui.manager.ManagerView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class ThematicMenuCommand implements Command {

    public static final String THEMATIC_MENU_MANAGEMENT = "Gestione menu tematici";
    public static final String VIEW_THEMATICS_MENU = "Visualizza menu tematici";
    public static final String MAKE_NEW_THEMATIC_MENU = "Crea nuovo menu tematico";
    public static final String THEMATIC_MENU_NAME = "Nome del menu tematico: ";
    public static final String START_VALIDITY = "Data di inizio validità: ";
    public static final String STOP_VALIDITY = "Data di fine validità: ";
    public static final String DISH_NAME_TO_ADD = "Nome del piatto da inserire nel menu: ";
    public static final String ADD_ANOTHER_DISH_Y_OR_NO = "Vuoi inserire un altro piatto? (y)es/(n)o: ";
    private final Restaurant restaurant = Restaurant.getInstance();
    private final ManagerView view;

    public ThematicMenuCommand(ManagerView view) {
        this.view = view;
    }

    /**
     * @see Command
     */
    @Override
    public void execute() {
        final BaseMenu menu = new BaseMenu(THEMATIC_MENU_MANAGEMENT);

        menu.addEntry(VIEW_THEMATICS_MENU, () -> {
            final Set<ThematicMenu> thematicMenus = restaurant.getThematicMenus();

            if (thematicMenus.isEmpty()) {
                view.printEmptyList();
            } else {
                view.printThematicSaved();
                thematicMenus.forEach(System.out::println);
            }
        });
        menu.addEntry(MAKE_NEW_THEMATIC_MENU, () -> {
            final Set<Dish> dishes = restaurant.getDishes();

            if (dishes.isEmpty()) {
                view.printNoDishesAvailable();
                return;
            }

            final String name = InputManager.readString(THEMATIC_MENU_NAME);
            final LocalDate startDate = InputManager.readDate(START_VALIDITY,
                    InputManager.DEFAULT_DATE_FORMATTER_PATTERN);
            final LocalDate expireDate = InputManager.readDate(STOP_VALIDITY,
                    InputManager.DEFAULT_DATE_FORMATTER_PATTERN);

            if (startDate.isAfter(expireDate)) {
                view.printDateBeforeStart();
                return;
            }

            final List<Dish> menuDishes = new ArrayList<>();
            do {
                final String dishName = InputManager.readString(DISH_NAME_TO_ADD);
                final Optional<Dish> optionalDish = restaurant.getDish(dishName);

                if (optionalDish.isEmpty()) {
                    view.printNoDish(dishName);
                    continue;
                }

                final Dish dish = optionalDish.get();

                if (dish.getRecipe() == null) {
                    view.printNotPaired();
                    continue;
                }

                if (isDishCompatible(dish, menuDishes)) {
                    menuDishes.add(dish);
                    view.printDishAdded();
                } else {
                    view.printWorkloadExceeded();
                }
            } while (InputManager.readYesOrNo(ADD_ANOTHER_DISH_Y_OR_NO));

            restaurant.getThematicMenus().add(new ThematicMenu(
                    name,
                    new Period(startDate, expireDate),
                    menuDishes));
        });

        menu.run();
    }

    public boolean isDishCompatible(Dish dish, List<Dish> dishes) {
        return dishes.stream()
                .mapToDouble(Dish::getWorkload)
                .sum() + dish.getWorkload() < (float) 4 / 3 * restaurant.getIndividualWorkload();
    }
}
