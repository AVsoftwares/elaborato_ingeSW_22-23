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
        var recipeBook = restaurant.getRecipeBook();

        System.out.println("Attualmente sono presenti nel ricettario \"" + recipeBook.getName() + "\" le seguenti ricette:");

        recipeBook.getRecipes().forEach(r -> {
            System.out.println("- " + r.getName());
        });
    }

}
