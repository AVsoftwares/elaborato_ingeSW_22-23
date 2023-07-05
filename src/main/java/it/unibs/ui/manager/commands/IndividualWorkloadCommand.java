package it.unibs.ui.manager.commands;

import it.unibs.core.Restaurant;
import it.unibs.ui.Command;
import it.unibs.ui.InputManager;

public class IndividualWorkloadCommand implements Command {

    private final Restaurant restaurant;

    public IndividualWorkloadCommand(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void execute() {
        final var workload = restaurant.getIndividualWorkload();

        if (workload == 0) {
            System.out.println("Il valore non è ancora stato inizializzato.");

            final int individualWorkload = InputManager.readInt("Inserisci il carico di lavoro per persona: ", 0, Integer.MAX_VALUE);
            restaurant.setIndividualWorkload(individualWorkload);
        } else {
            System.out.println("Il valore attuale è: " + workload);
        }
    }
}
