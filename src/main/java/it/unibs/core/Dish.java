package it.unibs.core;

import it.unibs.core.unit.Quantity;

import java.time.Clock;
import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;

/**
 * Classe che rappresenta un piatto, identificato univocamente da un nome,
 * associato ad una ricetta e con un periodo di validità
 */
public class Dish implements Consumable, Perishable {
    /**
     * Nome univoco che identifica il piatto
     */
    private final String name;
    /**
     * Ricetta associata al piatto
     */
    private Recipe recipe;
    /**
     * Periodo di validità del piatto
     */
    private final Period period;
    private boolean isAvailable = false;

    public Dish(String name, Period period) {
        this.name = Objects.requireNonNull(name);
        this.period = Objects.requireNonNull(period);
    }

    /**
     * @return carico di lavoro per porzione del piatto
     */
    public float getWorkload() {
        return recipe.getPortionWorkload();
    }

    public String getName() {
        return name;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public void setAvailable(boolean value){
        isAvailable = value;
    }

    public boolean isAvailable(){
        return isAvailable;
    }

    @Override
    public Map<? extends Product, Quantity> getProductsQuantity() {
        return recipe.getIngredients();
    }

    /**
     * Metodo di validazione di un piatto
     *
     * @return true se piatto disponibile nel momento della verifica
     * @see #isExpiredAtDate(LocalDate)
     */
    @Override
    public boolean isExpired(Clock clock) {
        return isExpiredAtDate(LocalDate.now(clock));
    }

    /**
     * Metodo di validazione di un piatto, rispetto ad una data
     *
     * @param date la data per cui bisogna verificare se il piatto è disponibile
     * @return true se il piatto è valido alla data specificata, false altrimenti
     */
    @Override
    public boolean isExpiredAtDate(LocalDate date) {
        return !period.includes(date);
    }

    /**
     * Metodo per verificare se due Dish sono equivalenti
     *
     * @param o oggetto rispetto al quale viene verificata l'equivalenza
     * @return true se i due oggetti sono riferimenti alla stessa istanza o se entrambi sono istanze di
     * Dish ed hanno nome uguale (case insensitive), false altrimenti
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return name.equalsIgnoreCase(dish.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
