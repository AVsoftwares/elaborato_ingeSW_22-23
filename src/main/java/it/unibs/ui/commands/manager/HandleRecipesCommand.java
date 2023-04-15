package it.unibs.ui.commands.manager;

import java.util.Scanner;

import it.unibs.core.Restaurant;
import it.unibs.ui.Command;

public class HandleRecipesCommand implements Command {

    private final Restaurant restaurant;

    public HandleRecipesCommand(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void onSelection(Scanner scanner) {

    }
}
