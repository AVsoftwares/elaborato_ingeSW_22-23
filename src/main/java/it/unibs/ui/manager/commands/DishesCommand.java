package it.unibs.ui.manager.commands;

import it.unibs.core.Dish;
import it.unibs.core.Restaurant;
import it.unibs.ui.Command;
import it.unibs.ui.InputManager;
import it.unibs.ui.Menu;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
public class DishesCommand implements Command {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yy");
    private final Restaurant restaurant;

    @Override
    public void onSelection() {
        List<Dish> dishes = restaurant.getDishes();

        Menu menu = new Menu("Gestione piatti");
        menu.addEntry("Aggiungi piatti", () -> {
            if (!dishes.isEmpty()) {
                System.out.println("La lista di piatti è già stata inizializzata.");
                return;
            }

            do {
                final String name = InputManager.readString("Nome: ");
                final LocalDate startDate = InputManager.readDate("Data di inizio validità (dd/MM/yy): ",
                        DATE_FORMATTER);
                final LocalDate expireDate = InputManager.readDate("Data di fine validità (dd/MM/yy): ",
                        DATE_FORMATTER);

                dishes.add(new Dish(name, startDate, expireDate));
            } while (InputManager.readYesOrNo("Vuoi inserire un altro piatto?  (y)es/(n)o: "));
        });
        menu.addEntry("Visualizza piatti", () -> {
            if (dishes.isEmpty()) {
                System.out.println("Non sono memorizzati piatti.");
                return;
            }

            for (int i = 0; i < dishes.size(); i++) {
                System.out.println(i + "\t" + dishes.get(i).getName());
            }
        });
        menu.run();
    }
}
