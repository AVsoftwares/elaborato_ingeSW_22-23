package it.unibs.ui.commands.manager;

import it.unibs.core.Restaurant;

public class HandleExtraAmountCommand implements Command {

    private final Restaurant restaurant;

    public HandleExtraAmountCommand(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void onSelection() {

    }
}
