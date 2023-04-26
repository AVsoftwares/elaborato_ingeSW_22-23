package it.unibs.ui.manager.commands;

import java.time.format.DateTimeFormatter;
import java.util.List;

import it.unibs.core.Dish;
import it.unibs.core.Restaurant;
import it.unibs.ui.Command;
import it.unibs.ui.InputManager;

public class HandleDishesCommand implements Command {

    private final Restaurant restaurant;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yy");

    public HandleDishesCommand(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void onSelection() {
        var exit = false;

        while (!exit) {
            System.out.println("Attualmente sono memorizzati i seguenti piatti:");

            List<Dish> dishes = restaurant.getDishes();
            for (int i = 0; i < dishes.size(); i++) {
                System.out.println("(" + i + 1 + "): " + dishes.get(i).getName());
            }

            var dish = dishes.get(InputManager.readInt("Scegli quale piatto vuoi modificare: ", 1, dishes.size()));

            final var editName = InputManager
                    .readYesOrNo("Il nome corrente è: " + dish.getName() + ", vuoi modificarlo? (y)es/(n)o: ");
            if (editName) {
                dish.setName(InputManager.readString("Inserisci il nuovo nome del piatto: "));
            }

            final var startDateString = dish.getStartDate().format(DATE_FORMATTER);
            final var expireDateString = dish.getStartDate().format(DATE_FORMATTER);

            final var editValidity = InputManager.readYesOrNo("Il periodo di validità corrente va dal "
                    + startDateString + " al " + expireDateString + ", vuoi modificarlo? (y)es/(n)o: ");
            if (editValidity) {
                dish.setStartDate(
                        InputManager.readDate("Inserisci la data di inizio validità (dd/MM/yy): ", DATE_FORMATTER));
                dish.setExpireDate(
                        InputManager.readDate("Inserisci la data di fine validità (dd/MM/yy): ", DATE_FORMATTER));
            }

            exit = !InputManager.readYesOrNo("Vuoi modificare un altro piatto? (y)es/(n)o: ");
        }
    }
}
