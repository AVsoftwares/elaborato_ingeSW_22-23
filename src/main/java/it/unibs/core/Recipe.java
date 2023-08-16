package it.unibs.core;

import it.unibs.core.unit.Quantity;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Una ricetta costituita da coppie ingrediente-quantità, un numero di porzioni che si riescono a preparare e
 * un carico di lavoro
 */
public class Recipe {
    /**
     * Coppie ingrediente-quantità
     */
    private final Map<Ingredient, Quantity> ingredients;
    /**
     * Numero intero di porzioni che si possono preparare con la ricetta
     */
    private final int portions;
    /**
     * Carico di lavoro per ciascuna porzione preparata
     */
    private final float portionWorkload;

    /**
     * @param ingredients     coppie di ingredienti e quantità relative componenti la ricetta
     * @param portions        numero di porzioni ottenibili dalla ricetta
     * @param portionWorkload carico di lavoro per porzione associato alla ricetta
     */
    public Recipe(Map<Ingredient, Quantity> ingredients, int portions, float portionWorkload) {
        this.ingredients = Objects.requireNonNull(ingredients);
        this.portions = portions;
        this.portionWorkload = portionWorkload;
    }

    /**
     * Aggiunge un ingrediente alla ricetta
     *
     * @param ingredient ingrediente da aggiungere alla ricetta
     * @param amount     quantità dell'ingrediente necessaria per la ricetta
     */
    public void add(Ingredient ingredient, Quantity amount) {
        ingredients.put(ingredient, amount);
    }

    /**
     * Rimuove un ingrediente dalla ricetta
     *
     * @param ingredient ingrediente da rimuovere dalla ricetta
     */
    public void remove(Ingredient ingredient) {
        ingredients.remove(ingredient);
    }

    /**
     * Controlla se un ingrediente è contenuto nella ricetta
     *
     * @param ingredient ingrediente da controllare se presente nella ricetta
     * @return true se ingrediente presente in ricetta, false altrimenti
     */
    public boolean contains(Ingredient ingredient) {
        return ingredients.containsKey(ingredient);
    }

    /**
     * getter che ritorna una Map immutabile per evitare un uso improprio dal client
     *
     * @return Map immutabile di coppie ingrediente-quantità
     */
    public Map<Ingredient, Quantity> getIngredients() {
        return Collections.unmodifiableMap(ingredients);
    }

    public float getPortionWorkload() {
        return portionWorkload;
    }

    @Override
    public String toString() {
        var ingredientsString = ingredients.keySet().stream().map(Object::toString).collect(Collectors.joining(", "));

        return "Ingredienti: " + ingredientsString + "\n\tPorzioni: " + portions
                + "\n\tCarico di lavoro per porzione: " + portionWorkload;
    }
}
