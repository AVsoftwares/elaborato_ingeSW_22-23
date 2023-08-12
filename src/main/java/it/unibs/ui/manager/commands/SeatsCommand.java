package it.unibs.ui.manager.commands;

import it.unibs.core.Restaurant;
import it.unibs.ui.Command;
import it.unibs.ui.InputManager;

public class SeatsCommand implements Command {

    public static final String VALUE_NOT_INITIALIZED = "Il valore non è ancora stato inizializzato.";
    public static final String RESTAURANT_SEATS = "Inserisci il numero di posti a sedere del ristorante: ";
    public static final String ACTUAL_VALUE = "Il valore attuale è: ";
    private final Restaurant restaurant;

    public SeatsCommand(Restaurant restaurant) {
        this.restaurant = Restaurant.getInstance();
    }

    @Override
    public void execute() {
        final var seats = restaurant.getSeats();

        if (seats == 0) {
            System.out.println(VALUE_NOT_INITIALIZED);
            restaurant.setSeats(InputManager.readInt(RESTAURANT_SEATS));
        } else {
            System.out.println(ACTUAL_VALUE + seats);
        }
    }
}
