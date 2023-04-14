package it.unibs.ui.commands.manager;

import it.unibs.core.Restaurant;

public class HandleRecipesCommand implements Command {

    private final Restaurant restaurant;

    public HandleRecipesCommand(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void onSelection() {

    }
}
