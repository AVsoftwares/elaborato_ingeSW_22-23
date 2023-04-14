package it.unibs.ui.commands.manager;

import it.unibs.core.Restaurant;

public class HandleDrinkAmountCommand implements Command {
    private final Restaurant restaurant;

    public HandleDrinkAmountCommand(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void onSelection() {

    }
}
