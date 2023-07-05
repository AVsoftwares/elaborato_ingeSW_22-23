package it.unibs.ui.manager.commands;

import it.unibs.core.Restaurant;
import it.unibs.ui.Command;
import it.unibs.ui.InputManager;

public class SeatsCommand implements Command {

    private final Restaurant restaurant;

    public SeatsCommand(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void execute() {
        final var seats = restaurant.getSeats();

        if (seats == 0) {
            System.out.println("Il valore non è ancora stato inizializzato.");
            restaurant.setSeats(InputManager.readInt("Inserisci il numero di posti a sedere del ristorante: "));
        } else {
            System.out.println("Il valore attuale è: " + seats);
        }
    }
}
