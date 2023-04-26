package it.unibs.ui.manager.commands;

import it.unibs.core.Restaurant;
import it.unibs.ui.Command;

public class ViewRecipesCommand implements Command {

    private final Restaurant restaurant;

    public ViewRecipesCommand(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void onSelection() {
        System.out.println("Attualmente sono presenti nel ricettario le seguenti ricette:");

        restaurant.getRecipes().forEach(r -> {
            System.out.println("- " + r.toString());
        });
    }

}
