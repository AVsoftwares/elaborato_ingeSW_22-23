package it.unibs.ui.manager.commands;

import it.unibs.core.Restaurant;
import it.unibs.ui.Command;
import it.unibs.ui.InputManager;

public class HandleIndividualWorkloadCommand implements Command {

    private final Restaurant restaurant;

    public HandleIndividualWorkloadCommand(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void onSelection() {
        final var workload = restaurant.getIndividualWorkload();

        if (workload == 0) {
            System.out.println("Il valore non è ancora stato inizializzato.");
            restaurant.setIndividualWorkload(InputManager.readInt());
        } else {
            System.out.println("Il valore attuale è: " + workload);
        }
    }
}