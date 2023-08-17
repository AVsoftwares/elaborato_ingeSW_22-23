package it.unibs.ui.manager.commands;

import it.unibs.core.Restaurant;
import it.unibs.ui.Command;
import it.unibs.ui.InputManager;

public class IndividualWorkloadCommand implements Command {

    public static final String VALUE_NOT_INITIALIZED = "Il valore non è ancora stato inizializzato.";
    public static final String ADD_PERSON_WORKLOAD = "Inserisci il carico di lavoro per persona: ";
    public static final String ACTUAL_VALUE = "Il valore attuale è: ";
    private final Restaurant restaurant = Restaurant.getInstance();

    @Override
    public void execute() {
        final var workload = restaurant.getIndividualWorkload();

        if (workload == 0) {
            System.out.println(VALUE_NOT_INITIALIZED);

            final int individualWorkload = InputManager.readInt(ADD_PERSON_WORKLOAD, 0, Integer.MAX_VALUE);
            restaurant.setIndividualWorkload(individualWorkload);
        } else {
            System.out.println(ACTUAL_VALUE + workload);
        }
    }
}
