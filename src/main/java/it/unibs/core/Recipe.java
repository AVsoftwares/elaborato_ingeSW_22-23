package it.unibs.core;

import java.util.Map;

public class Recipe {
    private String recipeName;
    private int portions;
    private int portionWokload;
    private int personWokload;
    private int preparationTime;
    private Map<Ingredient, Float> ingredientAmount;

    public Recipe(Map<Ingredient, Float> ingredientAmount) {
        this.ingredientAmount = ingredientAmount;
    }

    public Ingredient[] getIngredients() {
        return new Ingredient[]{};
    }

    public float getIngredientAmount(Ingredient ingredient) {
        return ingredientAmount.getOrDefault(ingredient, 0f);
    }

    public void setPortionWorkLoad() {

    }

    public int getPortionWorkLoad(){
        return this.portionWokload;
    }
}
