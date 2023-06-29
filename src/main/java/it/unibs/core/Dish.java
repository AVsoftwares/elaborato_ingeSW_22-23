package it.unibs.core;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Dish {
    public enum CourseType {
        STARTER, FIRST_COURSE, SECOND_COURSE, DESSERT;
    }

    private final String name;
    private Recipe recipe;
    private CourseType type;
    private int preparationTime;
    private LocalDate startDate;
    private LocalDate expireDate;

    public Dish(String name, LocalDate startDate, LocalDate expireDate) {
        this.name = name;
        this.startDate = startDate;
        this.expireDate = expireDate;
    }

    /**
     *
     * @return true se piatto disponibile al momento della verifica
     */
    public boolean isAvailable() {
        return isAvailableAtDate(LocalDate.now());
    }

    public boolean isAvailableAtDate(LocalDate date) {
        return date.isAfter(startDate) && date.isBefore(expireDate);
    }

    /**
     *
     * @return carico di lavoro per porzione del piatto
     */
    public int getWorkload() {
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
        var startDateString = startDate.format(DateTimeFormatter.ofPattern("dd/MM/yy"));
        var expireDateString = expireDate.format(DateTimeFormatter.ofPattern("dd/MM/yy"));

        return "Nome: " + name + "\n\tTipo: " + type.toString() + "\n\tTempo di preparazione: " + preparationTime
        + "\n\tValido dal " + startDateString + " al " + expireDateString;
    }
}
