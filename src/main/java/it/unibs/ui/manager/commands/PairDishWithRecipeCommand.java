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

    public static final String NO_PRESENT_DISHES_IMPOSSIBLE_PROCEED = "Non sono presenti piatti, impossibile procedere.";
    public static final String ALL_DISHES_PAIRED = "Tutti i piatti sono associati ad una ricetta.";
    public static final String NO_PRESENT_RECIPES_IMPOSSIBLE_PROCEED = "Non sono presenti ricette, impossibile procedere.";
    public static final String DISHES_NOT_PAIRED = "I seguenti piatti non hanno una ricetta associata: ";
    public static final String DISH_TO_PAIR = "Nome del piatto da associare: ";
    public static final String DISH_NOT_IN_LIST = "Il piatto non è presente nella lista.";
    public static final String DISH_ALREADY_PAIRED = "Il piatto ha già una ricetta associata";
    public static final String MSG_SAVED_RECIPES = "Sono memorizzate le seguenti ricette: ";
    public static final String RECIPE_INDEX_TO_PAIR = "Indice della ricetta da assegnare: ";
    public static final String ANOTHER_DISH_Y_OR_NO = "Vuoi modificare un altro piatto? (y)es/(n)o: ";
    private final Restaurant restaurant = Restaurant.getInstance();

    @Override
    public void execute() {
        Set<Dish> dishes = restaurant.getDishes();

        if (dishes.isEmpty()) {
            System.out.println(NO_PRESENT_DISHES_IMPOSSIBLE_PROCEED);
            return;
        }
        if (dishes.stream().noneMatch(d -> d.getRecipe() == null)) {
            System.out.println(ALL_DISHES_PAIRED);
            return;
        }

        List<Recipe> recipes = restaurant.getRecipes();
        if (recipes.isEmpty()) {
            System.out.println(NO_PRESENT_RECIPES_IMPOSSIBLE_PROCEED);
            return;
        }

        do {
            final List<Dish> dishesWithoutRecipe = dishes.stream().filter(d -> d.getRecipe() == null).toList();

            System.out.println(DISHES_NOT_PAIRED);
            dishesWithoutRecipe.forEach(System.out::println);

            final String dishName = InputManager.readString(DISH_TO_PAIR);
            final Optional<Dish> optionalDish = restaurant.getDish(dishName);

            if (optionalDish.isEmpty()) {
                System.out.println(DISH_NOT_IN_LIST);
                continue;
            }

            final Dish dish = optionalDish.get();

            if (!dishesWithoutRecipe.contains(dish)) {
                System.out.println(DISH_ALREADY_PAIRED);
                continue;
            }

            System.out.println(MSG_SAVED_RECIPES);
            for (int i = 0; i < recipes.size(); i++) {
                System.out.println("\t- " + i + recipes.get(i));
            }
            final int recipeIndex = InputManager.readInt(RECIPE_INDEX_TO_PAIR, 0, recipes.size());

            dish.setRecipe(recipes.get(recipeIndex));
        } while (InputManager.readYesOrNo(ANOTHER_DISH_Y_OR_NO));
    }
}
