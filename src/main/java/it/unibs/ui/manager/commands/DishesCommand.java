package it.unibs.ui.manager.commands;

import java.time.format.DateTimeFormatter;
import java.util.List;

import it.unibs.core.Dish;
import it.unibs.core.Restaurant;
import it.unibs.ui.Command;
import it.unibs.ui.InputManager;
import it.unibs.ui.Menu;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DishesCommand implements Command {
    
    private final Restaurant restaurant;
    
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yy");

    @Override
    public void onSelection() {
        do {
            System.out.println("Attualmente sono memorizzati i seguenti piatti:");

            List<Dish> dishes = restaurant.getDishes();
            for (int i = 0; i < dishes.size(); i++) {
                System.out.println(i + "\t" + dishes.get(i).getName());
            }

            var dish = dishes.get(InputManager.readInt("Scegli quale piatto vuoi modificare: ", 1, dishes.size()));

            Menu menu = new Menu("Modifica piatto");

            menu.addEntry("Modifica nome", () -> {
                System.out.println("Il nome è: " + dish.getName());

                if (InputManager.readYesOrNo("Vuoi modificarlo? (y)es/(n)o: ")) {
                    dish.setName(InputManager.readString("Inserisci il nome del piatto: "));
                }
            });

            menu.addEntry("Modifica periodo di validità", () -> {
                final var startDateString = dish.getStartDate().format(DATE_FORMATTER);
                final var expireDateString = dish.getStartDate().format(DATE_FORMATTER);

                System.out.println("Piatto valido dal " + startDateString + " al " + expireDateString);

                if (InputManager.readYesOrNo("Vuoi modificarlo? (y)es/(n)o: ")) {
                    dish.setStartDate(
                            InputManager.readDate("Inserisci la data di inizio validità (dd/MM/yy): ",
                                    DATE_FORMATTER));
                    dish.setExpireDate(
                            InputManager.readDate("Inserisci la data di fine validità (dd/MM/yy): ",
                                    DATE_FORMATTER));
                }
            });

        } while (InputManager.readYesOrNo("Vuoi modificare un altro piatto? (y)es/(n)o: "));
    }
}
