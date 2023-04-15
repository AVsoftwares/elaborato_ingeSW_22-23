package it.unibs.ui.commands.manager;

import java.util.Scanner;

import it.unibs.core.Restaurant;

public class HandleExtraAmountCommand implements Command {

    private final Restaurant restaurant;

    public HandleExtraAmountCommand(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void onSelection(Scanner scanner) {

    }
}
