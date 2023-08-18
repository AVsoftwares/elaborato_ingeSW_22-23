package it.unibs.ui.manager.commands;

import it.unibs.core.Restaurant;
import it.unibs.ui.Command;
import it.unibs.ui.InputManager;
import it.unibs.ui.manager.ManagerView;

public class IndividualWorkloadCommand implements Command {

    public static final String ADD_PERSON_WORKLOAD = "Inserisci il carico di lavoro per persona: ";
    private final Restaurant restaurant = Restaurant.getInstance();
    private final ManagerView view;

    public IndividualWorkloadCommand(ManagerView view) {
        this.view = view;
    }

    @Override
    public void execute() {
        final var workload = restaurant.getIndividualWorkload();

        if (workload == 0) {
            view.printValueNotInitizialized();

            final int individualWorkload = InputManager.readInt(ADD_PERSON_WORKLOAD, 0, Integer.MAX_VALUE);
            restaurant.setIndividualWorkload(individualWorkload);
        } else {
            view.printActualValue(workload);
        }
    }
}
