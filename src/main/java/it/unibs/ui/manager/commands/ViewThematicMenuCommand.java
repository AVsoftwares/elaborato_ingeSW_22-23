package it.unibs.ui.manager.commands;

import it.unibs.core.Restaurant;
import it.unibs.ui.Command;

public class ViewThematicMenuCommand implements Command {

    private final Restaurant restaurant;

    public ViewThematicMenuCommand(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void onSelection() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onSelection'");
    }

}
