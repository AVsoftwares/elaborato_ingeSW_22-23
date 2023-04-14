package it.unibs.core;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RecipeBook {
    private int recipesNumber = 0;
    private String bookTitle;
    private String restaurant;

    List<Recipe> recipes = new ArrayList<>();

    public RecipeBook(String restaurantName, String bookTitle) {
        this.restaurant = restaurantName;
        this.bookTitle = bookTitle;
    }

    public void addRecipe(Recipe recipe) {
        if (recipes.contains(recipe)) {
            System.out.println("Ricetta già presente.");
        } else {
            recipes.add(recipe);
        }
    }

    public void deleteRecipe(Recipe recipe) {
        recipes.removeIf(r -> r.equals(recipe));
    }

    public void showRecipe(Recipe recipe) {

    }

    public void showRecipeBook() {
        //TODO: si potrebbe fare overload di un toString in Recipe da mostrare
        //TODO io toString lo userei solo per debug, uno StringBuilder qua nel metodo è sufficiente
    }
}
