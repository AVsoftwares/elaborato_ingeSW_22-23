package it.unibs.ui.manager.commands;

import it.unibs.core.Restaurant;
import it.unibs.ui.Command;

public class IndividualWorkloadView implements Command {

    private final Restaurant restaurant;
    private final IndividualWorkloadController controller = new IndividualWorkloadController();

    public IndividualWorkloadView(Restaurant restaurant) {
        this.restaurant = Restaurant.getInstance();
    }

    @Override
    public void execute() {
        controller.individualWorkloadController(restaurant);
    }
}
