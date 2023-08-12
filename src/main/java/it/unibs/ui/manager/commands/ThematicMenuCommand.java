package it.unibs.ui.manager.commands;

import it.unibs.core.Dish;
import it.unibs.core.Period;
import it.unibs.core.Restaurant;
import it.unibs.core.ThematicMenu;
import it.unibs.ui.Command;
import it.unibs.ui.InputManager;
import it.unibs.ui.Menu;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class ThematicMenuCommand implements Command {

    public static final String THEMATIC_MENU_MANAGEMENT = "Gestione menu tematici";
    public static final String VIEW_THEMATICS_MENU = "Visualizza menu tematici";
    public static final String MSG_EMPTY_LIST = "La lista è vuota.";
    public static final String THEMATIC_MENU_SAVED = "I menu tematici memorizzati sono:";
    public static final String MAKE_NEW_THEMATIC_MENU = "Crea nuovo menu tematico";
    public static final String NO_DISH_AVAILABLE = "Nessun piatto disponibile, impossibile continuare.";
    public static final String THEMATIC_MENU_NAME = "Nome del menu tematico: ";
    public static final String START_VALIDITY = "Data di inizio validità: ";
    public static final String STOP_VALIDITY = "Data di fine validità: ";
    public static final String EXPIRATION_DATE_BEFORE_START_DATE_VALIDITY = "La data di scadenza inserita è precedente alla data di inizio validità, impossibile continuare.";
    public static final String DISH_NAME_TO_ADD = "Nome del piatto da inserire nel menu: ";
    public static final String NO_DISH = "Nessun piatto ";
    public static final String NO_PAIRED_RECIPE_DISH = "Il piatto non ha una ricetta associata, non è possibile inserirlo nel menu.";
    public static final String DISH_ADDED = "Piatto aggiunto al menu.";
    public static final String DISH_WORKLOAD_EXCEDED = "Il piatto scelto ha un carico di lavoro incompatibile con il menu.";
    public static final String ADD_ANOTHER_DISH_Y_OR_NO = "Vuoi inserire un altro piatto? (y)es/(n)o: ";
    private final Restaurant restaurant;

    public ThematicMenuCommand(Restaurant restaurant) {
        this.restaurant = Restaurant.getInstance();
    }

    /**
     * @see Command
     */
    @Override
    public void execute() {
        final Menu menu = new Menu(THEMATIC_MENU_MANAGEMENT);

        menu.addEntry(VIEW_THEMATICS_MENU, () -> {
            final Set<ThematicMenu> thematicMenus = restaurant.getThematicMenus();

            if (thematicMenus.isEmpty()) {
                System.out.println(MSG_EMPTY_LIST);
            } else {
                System.out.println(THEMATIC_MENU_SAVED);
                thematicMenus.forEach(System.out::println);
            }
        });
        menu.addEntry(MAKE_NEW_THEMATIC_MENU, () -> {
            final Set<Dish> dishes = restaurant.getDishes();

            if (dishes.isEmpty()) {
                System.out.println(NO_DISH_AVAILABLE);
                return;
            }

            final String name = InputManager.readString(THEMATIC_MENU_NAME);
            final LocalDate startDate = InputManager.readDate(START_VALIDITY,
                    InputManager.DEFAULT_DATE_FORMATTER_PATTERN);
            final LocalDate expireDate = InputManager.readDate(STOP_VALIDITY,
                    InputManager.DEFAULT_DATE_FORMATTER_PATTERN);

            if (startDate.isAfter(expireDate)) {
                System.out.println(EXPIRATION_DATE_BEFORE_START_DATE_VALIDITY);
                return;
            }

            final List<Dish> menuDishes = new ArrayList<>();
            do {
                final String dishName = InputManager.readString(DISH_NAME_TO_ADD);
                final Optional<Dish> optionalDish = restaurant.getDish(dishName);

                if (optionalDish.isEmpty()) {
                    System.out.println(NO_DISH + dishName);
                    continue;
                }

                final Dish dish = optionalDish.get();

                if (dish.getRecipe() == null) {
                    System.out.println(NO_PAIRED_RECIPE_DISH);
                    continue;
                }

                if (isDishCompatible(dish, menuDishes)) {
                    menuDishes.add(dish);
                    System.out.println(DISH_ADDED);
                } else {
                    System.out.println(DISH_WORKLOAD_EXCEDED);
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
