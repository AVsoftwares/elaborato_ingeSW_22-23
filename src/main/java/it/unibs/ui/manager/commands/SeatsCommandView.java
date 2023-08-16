package it.unibs.ui.manager.commands;

import it.unibs.core.Restaurant;
import it.unibs.ui.Command;
import it.unibs.ui.InputManager;

public class SeatsCommandView implements Command {

    private final Restaurant restaurant;

    private final SeatsCommandController seatCommandController = new SeatsCommandController();


    public SeatsCommandView(Restaurant restaurant) {
        this.restaurant = Restaurant.getInstance();
    }

    @Override
    public void execute() {
        seatCommandController.seatController(restaurant);
    }
}
