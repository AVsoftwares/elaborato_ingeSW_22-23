package it.unibs.core;

import it.unibs.core.unit.Quantity;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Dish implements Orderable, Expire {
    /**
     * Nome univoco che identifica il piatto
     */
    private final String name;
    private Recipe recipe;
    private int preparationTime;
    private Period period;

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

    @Override
    public Map<? extends Product, Quantity> getProductsQuantity() {
        return recipe.getIngredients();
    }

    /**
     * @return true se piatto disponibile al momento della verifica
     */
    @Override
    public boolean isValid() {
        return isValidAtDate(LocalDate.now());
    }

    @Override
    public boolean isValidAtDate(LocalDate date) {
        return period.includes(date);
    }

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
