package it.unibs.core;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;
import java.util.Scanner;

@Getter
@Setter
@NoArgsConstructor
public class Recipe {
    private String recipeName;
    private int portions;
    private int portionWorkload;
    private Float personWorkload;
    private int preparationTime;
    private Map<Ingredient, Float> ingredientAmount;

    public Recipe(Map<Ingredient, Float> ingredientAmount, int portNum) {
        this.ingredientAmount = ingredientAmount;
        this.portions = portNum;
    }

    public void showIngredients() {
        for (var entry : ingredientAmount.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }

    public float getIngredientAmount(Ingredient ingredient) {
        return ingredientAmount.getOrDefault(ingredient, 0f);
    }

    public void addIngredient(Ingredient i, Float q) {
        this.ingredientAmount.put(i, q);
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

    public void createNewRecipe() {
        Recipe recipe = new Recipe();
        Scanner scanner = new Scanner(System.in);
        String inputIngredient = "";
        Integer ingredientCount = 0;
        System.out.println("Ciao, Per inserire una nuova ricetta scegli il nome: ");
        recipe.setRecipeName(scanner.nextLine());
        while (!inputIngredient.equalsIgnoreCase("N")) {
            ingredientCount++;
            System.out.printf("Inserisci l'ingrediente %d: ", ingredientCount);
            addIngredientToRecipe(recipe);
            System.out.println("Vuoi inserire un altro ingrediente (Y/N)?: ");
            inputIngredient = scanner.nextLine();

        }
        System.out.println("Quante porzioni puoi produrre con le dosi che hai inserito?: ");
        recipe.setPortions(scanner.nextInt());
        System.out.println("Qual'è il carico di lavoro per porzione di questa ricetta (numero decimale minore di uno)?:  ");
        recipe.setPersonWorkload(scanner.nextFloat());

        System.out.printf("La ricetta %s è stata inserita nel ricettario", recipe.getRecipeName());
    }

    public void addIngredientToRecipe(Recipe recipe) {

    }
}
