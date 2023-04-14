package it.unibs.ui.commands.manager;

import it.unibs.core.Restaurant;

import java.util.Scanner;

public class HandleIndividualWorkloadCommand implements Command {

    private final Restaurant restaurant;

    public HandleIndividualWorkloadCommand(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void onSelection() {
        final var workload = restaurant.getIndividualWorkLoad();
        Scanner scanner = new Scanner(System.in);

        if (workload == 0) {
            System.out.println("Il valore non è ancora stato inizializzato.");
            System.out.print("Inserisci il valore: ");
            restaurant.setIndividualWorkLoad(scanner.nextInt());
        } else {
            System.out.println("Il valore attuale è: " + workload);
        }
    }
}
