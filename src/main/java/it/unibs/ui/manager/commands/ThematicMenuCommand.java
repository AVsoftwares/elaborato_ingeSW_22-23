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

    private final Restaurant restaurant;

    public ThematicMenuCommand(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    /**
     * @see Command
     */
    @Override
    public void execute() {
        final Menu menu = new Menu("Gestione menu tematici");

        menu.addEntry("Visualizza menu tematici", () -> {
            final Set<ThematicMenu> thematicMenus = restaurant.getThematicMenus();

            if (thematicMenus.isEmpty()) {
                System.out.println("La lista è vuota.");
            } else {
                System.out.println("I menu tematici memorizzati sono:");
                thematicMenus.forEach(System.out::println);
            }
        });
        menu.addEntry("Crea nuovo menu tematico", () -> {
            final Set<Dish> dishes = restaurant.getDishes();

            if (dishes.isEmpty()) {
                System.out.println("Nessun piatto disponibile, impossibile continuare.");
                return;
            }

            final String name = InputManager.readString("Nome del menu tematico: ");
            final LocalDate startDate = InputManager.readDate("Data di inizio validità: ",
                    InputManager.DEFAULT_DATE_FORMATTER_PATTERN);
            final LocalDate expireDate = InputManager.readDate("Data di fine validità: ",
                    InputManager.DEFAULT_DATE_FORMATTER_PATTERN);

            if (startDate.isAfter(expireDate)) {
                System.out.println("La data di scadenza inserita è precedente alla data di inizio validità, impossibile continuare.");
                return;
            }

            final List<Dish> menuDishes = new ArrayList<>();
            do {
                final String dishName = InputManager.readString("Nome del piatto da inserire nel menu: ");
                final Optional<Dish> optionalDish = restaurant.getDish(dishName);

                if (optionalDish.isEmpty()) {
                    System.out.println("Nessun piatto " + dishName);
                    continue;
                }

                final Dish dish = optionalDish.get();

                if (isDishCompatible(dish, menuDishes)) {
                    menuDishes.add(dish);
                    System.out.println("Piatto aggiunto al menu.");
                } else {
                    System.out.println("Il piatto scelto ha un carico di lavoro incompatibile con il menu.");
                }
            } while (InputManager.readYesOrNo("Vuoi inserire un altro piatto? (y)es/(n)o: "));

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
