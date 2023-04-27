package it.unibs.ui.manager.commands;

import java.util.List;

import it.unibs.core.Dish;
import it.unibs.core.Recipe;
import it.unibs.core.Restaurant;
import it.unibs.ui.Command;
import it.unibs.ui.InputManager;
import lombok.RequiredArgsConstructor;

/**
 * Gestisce le corrispondenze tra piatti e ricette
 */
@RequiredArgsConstructor
public class HandleDishesRecipesCommand implements Command {

    private final Restaurant restaurant;

    @Override
    public void onSelection() {
        List<Dish> dishes = restaurant.getDishes();
        List<Recipe> recipes = restaurant.getRecipes();

        var exit = false;
        while (!exit) {
            System.out.println("Sono memorizzati i seguenti piatti: ");
            for (int i = 0; i < dishes.size(); i++) {
                System.out.println(i + "\t" + dishes.get(i));
            }

            final var dishIndex = InputManager.readInt("Inserisci quale vuoi modificare: ", 1, dishes.size());

            System.out.println("Sono memorizzate le seguenti ricette: ");
            for (int i = 0; i < recipes.size(); i++) {
                System.out.println(i + "\t" + recipes.get(i));
            }

            Recipe recipe = recipes.get(InputManager.readInt("Inserisci quale vuoi assegnare al piatto selezionato: ", 1, recipes.size()));
            dishes.get(dishIndex).setRecipe(recipe);

            System.out.println("La nuova corrispondenza piatto-ricetta è stata memorizzata.");

            exit = !InputManager.readYesOrNo("Vuoi modificare un altro piatto? (y)es/(n)o: ");
        }
    }
}
