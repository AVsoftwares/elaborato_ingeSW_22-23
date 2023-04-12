package it.unibs.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecipeBook {
    private int recipesNumber=0;
    private String bookTitle;
    private String restaurant;

    List<Recipe> recipes = new ArrayList<Recipe>();

    public RecipeBook(String restaurantName, String bookTitle){
        this.restaurant = restaurantName;
        this.bookTitle = bookTitle;
    }
    public void addRecipe(Recipe r){

        for (int i = 0; i < recipes.length; i++) {

            if(recipes[i].getName().equalsIgnoreCase(r.getName())){
                System.out.println("Ricetta già presente.");
            }
            else{
                recipes.add(r);
            }
        }
    }

    public Recipe[] deleteRecipe(Recipe[] recipes, String recipeName){
        //TODO: scrivere rimozione ricetta da ricettario
    }

    public void showRecipe(Recipe r){
        r.toString();
    }

    public void showRecipeBook(){
        //TODO: si potrebbe fare overload di un toString in Recipe da mostrare
    }
}
