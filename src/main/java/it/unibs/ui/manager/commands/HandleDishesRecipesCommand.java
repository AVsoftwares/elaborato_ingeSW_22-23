package it.unibs.ui.manager.commands;

import it.unibs.core.Restaurant;
import it.unibs.ui.Command;

public class HandleDishesRecipesCommand implements Command {

    private final Restaurant restaurant;

    public HandleDishesRecipesCommand(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void onSelection() {
        
    }
}
