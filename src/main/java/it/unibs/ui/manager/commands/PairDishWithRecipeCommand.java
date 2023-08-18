package it.unibs.ui.manager.commands;

import it.unibs.core.Dish;
import it.unibs.core.Recipe;
import it.unibs.core.Restaurant;
import it.unibs.ui.Command;
import it.unibs.ui.InputManager;
import it.unibs.ui.manager.ManagerView;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Gestisce le corrispondenze tra piatti e ricette
 */
public class PairDishWithRecipeCommand implements Command {

    public static final String DISH_TO_PAIR = "Nome del piatto da associare: ";
    public static final String RECIPE_INDEX_TO_PAIR = "Indice della ricetta da assegnare: ";
    public static final String ANOTHER_DISH_Y_OR_NO = "Vuoi modificare un altro piatto? (y)es/(n)o: ";
    private final Restaurant restaurant = Restaurant.getInstance();
    private final ManagerView view;

    public PairDishWithRecipeCommand(ManagerView view) {
        this.view = view;
    }

    @Override
    public void execute() {
        Set<Dish> dishes = restaurant.getDishes();

        if (dishes.isEmpty()) {
            view.printNoDishesSaved();
            return;
        }
        if (dishes.stream().noneMatch(d -> d.getRecipe() == null)) {
            view.printAllDishesPaired();
            return;
        }

        List<Recipe> recipes = restaurant.getRecipes();
        if (recipes.isEmpty()) {
            view.printNoRecipes();
            return;
        }

        do {
            final List<Dish> dishesWithoutRecipe = dishes.stream().filter(d -> d.getRecipe() == null).toList();

            view.printNoPairedDishesh();
            dishesWithoutRecipe.forEach(System.out::println);

            final String dishName = InputManager.readString(DISH_TO_PAIR);
            final Optional<Dish> optionalDish = restaurant.getDish(dishName);

            if (optionalDish.isEmpty()) {
                view.printDishNotPresent();
                continue;
            }

            final Dish dish = optionalDish.get();

            if (!dishesWithoutRecipe.contains(dish)) {
                view.printDishAlreadyPaired();
                continue;
            }

            view.printSavedRecipes();
            view.printRecipes(recipes);

            final int recipeIndex = InputManager.readInt(RECIPE_INDEX_TO_PAIR, 0, recipes.size());

            dish.setRecipe(recipes.get(recipeIndex));
        } while (InputManager.readYesOrNo(ANOTHER_DISH_Y_OR_NO));
    }
}
