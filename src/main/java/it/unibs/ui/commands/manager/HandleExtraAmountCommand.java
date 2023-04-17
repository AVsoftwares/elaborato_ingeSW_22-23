package it.unibs.ui.commands.manager;

import java.util.Scanner;

import it.unibs.core.Restaurant;
import it.unibs.ui.Command;

public class HandleExtraAmountCommand implements Command {

    private final Restaurant restaurant;

    public HandleExtraAmountCommand(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void onSelection(Scanner scanner) {

    }

    @Override
    public void onSelection() {

    }
}
