package it.unibs.ui.manager.commands;

import it.unibs.core.Dish;
import it.unibs.core.Recipe;
import it.unibs.core.Restaurant;
import it.unibs.ui.Command;
import it.unibs.ui.InputManager;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * Gestisce le corrispondenze tra piatti e ricette
 */
@RequiredArgsConstructor
public class PairDishWithRecipeCommand implements Command {

    private final Restaurant restaurant;

    @Override
    public void onSelection() {
        List<Dish> dishes = restaurant.getDishes();
        if (dishes.isEmpty()) {
            System.out.println("Non sono presenti piatti, impossibile procedere.");
            return;
        }
        if (dishes.stream().noneMatch(d -> d.getRecipe() == null)) {
            System.out.println("Tutti i piatti sono associati ad una ricetta.");
            return;
        }

        List<Recipe> recipes = restaurant.getRecipes();
        if (recipes.isEmpty()) {
            System.out.println("Non sono presenti ricette, impossibile procedere.");
            return;
        }

        do {
            final List<Dish> dishesWithoutRecipe = dishes.stream().filter(d -> d.getRecipe() == null).toList();
            System.out.println("I seguenti piatti non hanno una ricetta associata: ");
            for (int i = 0; i < dishesWithoutRecipe.size(); i++) {
                System.out.println(i + "\t" + dishesWithoutRecipe.get(i));
            }

            final int dishIndex = dishes.indexOf(dishesWithoutRecipe.get(
                    InputManager.readInt(
                            "Inserisci quale vuoi modificare: ", 0, dishesWithoutRecipe.size())));

            System.out.println("Sono memorizzate le seguenti ricette: ");
            for (int i = 0; i < recipes.size(); i++) {
                System.out.println(i + 1 + "\t" + recipes.get(i));
            }

            Recipe recipe = recipes.get(
                    InputManager.readInt(
                            "Inserisci quale vuoi assegnare al piatto selezionato: ", 0, recipes.size()));
            dishes.get(dishIndex).setRecipe(recipe);
        } while (InputManager.readYesOrNo("Vuoi modificare un altro piatto? (y)es/(n)o: "));
    }
}
