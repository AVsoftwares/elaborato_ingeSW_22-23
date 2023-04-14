package it.unibs.ui.commands.manager;

import it.unibs.core.Restaurant;

import java.util.Scanner;

public class HandleDrinksCommand implements Command {
    private final Restaurant restaurant;

    public HandleDrinksCommand(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void onSelection() {
    }
}
