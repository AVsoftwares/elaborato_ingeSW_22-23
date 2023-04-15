package it.unibs.ui.commands.manager;

import it.unibs.core.Restaurant;
import it.unibs.ui.Command;

import java.util.Scanner;

public class HandleSeatsCommand implements Command {

    private final Restaurant restaurant;

    public HandleSeatsCommand(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void onSelection(Scanner scanner) {
        final var seats = restaurant.getSeats();

        if (seats == 0) {
            System.out.println("Il valore non è ancora stato inizializzato.");
            System.out.print("Enter value: ");
            restaurant.setSeats(scanner.nextInt());
        } else {
            System.out.println("Il valore attuale è: " + seats);
        }
    }
}
