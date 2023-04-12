package it.unibs.core;

import java.util.Map;

public class Recipe {
    private String recipeName;
    private int portions;
    private int portionWorkLoad;
    private int personWokload;
    private int preparationTime;
    private Map<Ingredient, Float> ingredientAmount;

    public Recipe(Map<Ingredient, Float> ingredientAmount, int portNum) {
        this.ingredientAmount = ingredientAmount;
        this.portions = portNum;
    }

    public void showIngredients(){
        for (var entry : ingredientAmount.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }

    public float getIngredientAmount(Ingredient ingredient) {
        return ingredientAmount.getOrDefault(ingredient, 0f);
    }

    public void setPortionWorkLoad(int portionWL) {
        this.portionWorkLoad = portionWL;

    }

    public int getPortionWorkLoad(){
        return this.portionWorkLoad;
    }

    public int getPortions(){
        return this.portions;
    }

    public void addIngredient(Ingredient i, Float q){
        this.ingredientAmount.put(i, q);
    }

    public void deleteIngredient(Ingredient i){
        if (ingredientAmount.containsKey(i)){
            ingredientAmount.remove(i);
        }
        else{
            System.out.println("Ingrediente NON trovato!");
        }
    }

    public boolean containIngredient(Ingredient i){
        return this.ingredientAmount.containsKey(i);
    }

    public String getName(){
        return this.recipeName;
    }

}
