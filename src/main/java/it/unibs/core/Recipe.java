package it.unibs.core;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Recipe {
    private List<Ingredient> ingredients = new ArrayList<>();
    private int portions;
    private int portionWorkload;
    private Float personWorkload;
    private int preparationTime;

    /**
     *
     * @param ingredients lista di ingredienti componenti la ricetta
     * @param portions numero di porzioni ottenibili dalla ricetta
     */
    public Recipe(List<Ingredient> ingredients, int portions) {
        this.ingredients = ingredients;
        this.portions = portions;
    }

    /**
     * Mostra a video la lista di ingredienti componenti la ricetta con relative quantità se presenti, altrimenti
     * msg di assenza ingredienti.
     */
    public void showIngredients() {
        if (ingredients.isEmpty()) {
            System.out.println("Non sono presenti ingredienti");
        } else {
            System.out.println("Sono presenti " + ingredients.size() + " ingredienti.");
            for (var entry : ingredients) {
                System.out.println(entry.getName() + ": " + entry.getAmount() + " " + entry.getUnit());
            }
        }
    }

    /**
     * @param ingredient ingrediente di cui si vuol conoscere la quantità per la ricetta
     * @return quantitò dell'ingrediente per la ricetta
     */
    public float getIngredientAmount(Ingredient ingredient) {
        if (ingredients.contains(ingredient)) {
            return ingredients.get(ingredients.indexOf(ingredient)).getAmount();
        } else {
            return 0f;
        }
    }

    /**
     *
     * @param ingredient ingrediente da aggiungere alla ricetta
     */
    public void addIngredient(Ingredient ingredient) {
        if (ingredients.contains(ingredient)) {
            System.out.println("Ingrediente già presente.");
        } else {
            ingredients.add(ingredient);
        }
    }

    /**
     *
     * @param ingredient ingrediente da rimuovere dalla ricetta
     */
    public void deleteIngredient(Ingredient ingredient) {
        if (ingredients.contains(ingredient)) {
            ingredients.remove(ingredient);
        } else {
            System.out.println("Ingrediente non trovato!");
        }
    }

    /**
     *
     * @param ingredient
     * @return true se ingrediente presente in ricetta
     */
    public boolean containsIngredient(Ingredient ingredient) {
        return ingredients.contains(ingredient);
    }

    @Override
    public String toString() {
        var ingredientsString = ingredients.stream().map(Object::toString).collect(Collectors.joining(", "));

        return "Ingredienti: " + ingredientsString + "\n\tPorzioni: " + portions
                + "\n\tCarico di lavoro per porzione: " + portionWorkload
                + "\n\tCarico di lavoro per persona :" + personWorkload;
    }
}
