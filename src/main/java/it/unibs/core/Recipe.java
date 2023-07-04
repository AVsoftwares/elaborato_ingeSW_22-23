package it.unibs.core;

import it.unibs.core.unit.Quantity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class Recipe {
    private Map<Ingredient, Quantity> ingredients;
    private int portions;
    private float portionWorkload;
    private float personWorkload;
    private int preparationTime;

    /**
     * @param ingredients lista di ingredienti componenti la ricetta
     * @param portions    numero di porzioni ottenibili dalla ricetta
     */
    public Recipe(Map<Ingredient, Quantity> ingredients, int portions) {
        this.ingredients = ingredients;
        this.portions = portions;
    }

    /**
     * @param ingredient ingrediente da aggiungere alla ricetta
     * @param amount     quantità dell'ingrediente necessaria per la ricetta
     */
    public void addIngredient(Ingredient ingredient, it.unibs.core.unit.Quantity amount) {
        ingredients.put(ingredient, amount);
    }

    /**
     * @param ingredient ingrediente da rimuovere dalla ricetta
     */
    public void removeIngredient(Ingredient ingredient) {
        ingredients.remove(ingredient);
    }

    /**
     * @param ingredient ingrediente da controllare se presente nella ricetta
     * @return true se ingrediente presente in ricetta, false altrimenti
     */
    public boolean containsIngredient(Ingredient ingredient) {
        return ingredients.containsKey(ingredient);
    }

    @Override
    public String toString() {
        var ingredientsString = ingredients.keySet().stream().map(Object::toString).collect(Collectors.joining(", "));

        return "Ingredienti: " + ingredientsString + "\n\tPorzioni: " + portions
                + "\n\tCarico di lavoro per porzione: " + portionWorkload
                + "\n\tCarico di lavoro per persona :" + personWorkload;
    }
}
