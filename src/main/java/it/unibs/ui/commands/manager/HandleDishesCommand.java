package it.unibs.ui.commands.manager;

import java.util.Scanner;

import it.unibs.core.Restaurant;
import it.unibs.ui.Command;

public class HandleDishesCommand implements Command {

    private final Restaurant restaurant;

    public HandleDishesCommand(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void onSelection(Scanner scanner) {

    }
}
