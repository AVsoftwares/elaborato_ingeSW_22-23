package it.unibs.ui.manager.commands;

import it.unibs.core.Restaurant;
import it.unibs.ui.Command;
import it.unibs.ui.InputManager;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SeatsCommand implements Command {

    private final Restaurant restaurant;

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
