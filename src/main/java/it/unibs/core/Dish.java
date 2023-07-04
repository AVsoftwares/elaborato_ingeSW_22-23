package it.unibs.core;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter

public class Dish implements Expire {
    /**
     * Nome univoco che identifica il piatto
     */
    private final String name;
    private Recipe recipe;
    private CourseType type;
    private int preparationTime;
    private Period period;

    public Dish(String name, Period period) {
        this.name = name;
        this.period = period;
    }

    /**
     * @return true se piatto disponibile al momento della verifica
     */
    @Override
    public boolean isExpired() {
        return isExpiredAtDate(LocalDate.now());
    }

    @Override
    public boolean isExpiredAtDate(LocalDate date) {
        return period.isBefore(date);
    }

    /**
     * @return carico di lavoro per porzione del piatto
     */
    public float getWorkload() {
        return recipe.getPortionWorkload();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return name.equalsIgnoreCase(dish.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }

    public enum CourseType {
        STARTER, FIRST_COURSE, SECOND_COURSE, DESSERT
    }
}
