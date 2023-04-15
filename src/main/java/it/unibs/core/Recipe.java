package it.unibs.core;
import java.util.*;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class Recipe {
    private String recipeName;
    private int portions;
    private int portionWorkload;
    private Float personWorkload;
    private int preparationTime;
    private int ingredientNum =0;
    private HashMap<Ingredient, Float> ingredientAmount = new HashMap<Ingredient, Float>();
    private String name;


    public Recipe(String recipeName, int portNum) {
        this.recipeName = recipeName;
        this.portions = portNum;
    }

    public void showIngredients() {
        if(ingredientAmount.isEmpty()){
            System.out.println("Non sono presenti ingredienti");
        }
        else{
            System.out.println("Sono presenti " + ingredientNum + " ingredienti.");
            ingredientAmount.forEach((k,v) -> System.out.println("Ingrediente: "+k+" Quantita:" + v));
            }
    }


    public float getIngredientAmount(Ingredient ingredient) {
        return ingredientAmount.get(ingredient);
    }

    public void addIngredient(Ingredient i, Float q) {
        if(ingredientAmount.containsKey(i)){
            System.out.println("Ingrediente GIA' presente!");
            //qua magari si può chiedere di modificare la quantita dell'ingrediente gia inserito
        }
        else{
            this.ingredientAmount.put(i, q);
        }
    }

    public void deleteIngredient(Ingredient i) {
        if (ingredientAmount.containsKey(i)) {
            ingredientAmount.remove(i);
        } else {
            System.out.println("Ingrediente NON trovato!");
        }
    }

    public boolean containsIngredient(Ingredient i) {
        return this.ingredientAmount.containsKey(i);
    }

  /*  public void createNewRecipe() {
        Recipe recipe = new Recipe();
        Scanner scanner = new Scanner(System.in);
        String inputIngredient = "";
        Integer ingredientCount = 0;
        System.out.println("Ciao, Per inserire una nuova ricetta scegli il nome: ");
        recipe.setRecipeName(scanner.nextLine());
        while (!inputIngredient.equalsIgnoreCase("N")) {
            ingredientCount++;
            System.out.printf("Inserisci l'ingrediente %d: ", ingredientCount);
            addIngredient(i, q);
            System.out.println("Vuoi inserire un altro ingrediente (Y/N)?: ");
            inputIngredient = scanner.nextLine();

        }
        System.out.println("Quante porzioni puoi produrre con le dosi che hai inserito?: ");
        recipe.setPortions(scanner.nextInt());
        System.out.println("Qual'è il carico di lavoro per porzione di questa ricetta (numero decimale minore di uno)?:  ");
        recipe.setPersonWorkload(scanner.nextFloat());

        System.out.printf("La ricetta %s è stata inserita nel ricettario", recipe.getRecipeName());
    }
    
    public void modifyIngredientQuantity(Ingredient i, float newQuantity){
        if(!ingredientAmount.containsKey(i)){
            System.out.println("Ingrediente NON presente!");
            //qua magari si può chiedere di se lo si vuole inserire
        }
        else{
            ingredientAmount.put(i, newQuantity);
            }
        }*/
}
