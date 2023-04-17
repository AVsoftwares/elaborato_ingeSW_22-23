package it.unibs.ui.commands.manager;

import it.unibs.core.Restaurant;
import it.unibs.ui.Command;
import it.unibs.ui.InputManager;

public class HandleSeatsCommand implements Command {

    private final Restaurant restaurant;

    public HandleSeatsCommand(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void onSelection() {
        final var seats = restaurant.getSeats();

        if (seats == 0) {
            System.out.println("Il valore non è ancora stato inizializzato.");
            restaurant.setSeats(InputManager.readInt());
        } else {
            System.out.println("Il valore attuale è: " + seats);
        }
    }
}
