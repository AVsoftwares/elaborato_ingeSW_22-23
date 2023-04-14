package it.unibs.ui.commands.manager;

import it.unibs.core.Restaurant;

public class HandleDishesCommand implements Command {

    private final Restaurant restaurant;

    public HandleDishesCommand(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void onSelection() {

    }
}
