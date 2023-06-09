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

    private final Restaurant restaurant;

    public DishesCommand(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void execute() {
        Set<Dish> dishes = restaurant.getDishes();

        Menu menu = new Menu("Gestione piatti");
        menu.addEntry("Aggiungi piatti", () -> {
            if (!dishes.isEmpty()) {
                System.out.println("La lista di piatti è già stata inizializzata.");
                return;
            }

            do {
                final String name = InputManager.readString("Nome: ");
                final LocalDate startDate = InputManager.readDate("Data di inizio validità: ",
                        InputManager.DEFAULT_DATE_FORMATTER_PATTERN);
                final LocalDate expireDate = InputManager.readDate("Data di fine validità: ",
                        InputManager.DEFAULT_DATE_FORMATTER_PATTERN);

                if (startDate.isBefore(expireDate)) {
                    if (!dishes.add(new Dish(name, new Period(startDate, expireDate)))) {
                        System.out.println("Un piatto omonimo è già presente.");
                    }
                } else {
                    System.out.println("Il periodo inserito non è valido.");
                }
            } while (InputManager.readYesOrNo("Vuoi inserire un altro piatto?  (y)es/(n)o: "));
        });
        menu.addEntry("Visualizza piatti", () -> {
            if (dishes.isEmpty()) {
                System.out.println("Non sono memorizzati piatti.");
            } else {
                dishes.forEach(System.out::println);
            }
        });
        menu.run();
    }
}
