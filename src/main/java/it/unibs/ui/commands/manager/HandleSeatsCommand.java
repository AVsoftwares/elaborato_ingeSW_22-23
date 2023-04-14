package it.unibs.ui.commands.manager;

import it.unibs.core.Restaurant;

import java.util.Scanner;

public class HandleSeatsCommand implements Command {

    private final Restaurant restaurant;

    public HandleSeatsCommand(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void onSelection() {
        final var seats = restaurant.getSeats();
        Scanner scanner = new Scanner(System.in);

        if (seats == 0) {
            System.out.println("Il valore non è ancora stato inizializzato.");
            System.out.print("Enter value: ");
            restaurant.setSeats(scanner.nextInt());
        } else {
            System.out.println("Il valore attuale è: " + seats);
        }
    }
}
