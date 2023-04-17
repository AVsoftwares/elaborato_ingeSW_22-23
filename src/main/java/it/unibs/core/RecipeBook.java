package it.unibs.core;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RecipeBook {
    private String bookTitle;
    private List<Recipe> recipes = new ArrayList<>();

    public RecipeBook(String bookTitle) {
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
