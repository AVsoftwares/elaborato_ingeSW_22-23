package it.unibs.ui.commands.manager;

import it.unibs.core.Restaurant;

public class HandleExtraCommand implements Command {

    private final Restaurant restaurant;

    public HandleExtraCommand(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void onSelection() {

    }
}
