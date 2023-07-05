package it.unibs.ui.manager.commands;

import it.unibs.core.Dish;
import it.unibs.core.Recipe;
import it.unibs.core.Restaurant;
import it.unibs.ui.Command;
import it.unibs.ui.InputManager;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Gestisce le corrispondenze tra piatti e ricette
 */
public class PairDishWithRecipeCommand implements Command {

    private final Restaurant restaurant;

    public PairDishWithRecipeCommand(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void execute() {
        Set<Dish> dishes = restaurant.getDishes();

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
            dishesWithoutRecipe.forEach(System.out::println);

            final String dishName = InputManager.readString("Nome del piatto da associare: ");
            final Optional<Dish> optionalDish = restaurant.getDish(dishName);

            if (optionalDish.isEmpty()) {
                System.out.println("Il piatto non è presente nella lista.");
                continue;
            }

            final Dish dish = optionalDish.get();

            if (!dishesWithoutRecipe.contains(dish)) {
                System.out.println("Il piatto ha già una ricetta associata");
                continue;
            }

            System.out.println("Sono memorizzate le seguenti ricette: ");
            for (int i = 0; i < recipes.size(); i++) {
                System.out.println("\t- " + i + recipes.get(i));
            }
            final int recipeIndex = InputManager.readInt("Indice della ricetta da assegnare: ", 0, recipes.size());

            dish.setRecipe(recipes.get(recipeIndex));
        } while (InputManager.readYesOrNo("Vuoi modificare un altro piatto? (y)es/(n)o: "));
    }
}
