package it.unibs.ui.manager.commands;

import it.unibs.core.Restaurant;
import it.unibs.ui.Command;

public class CreateThematicMenuCommand implements Command {

    private final Restaurant restaurant;

    public CreateThematicMenuCommand(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void onSelection() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onSelection'");
    }

}
