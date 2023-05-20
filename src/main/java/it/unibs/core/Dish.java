package it.unibs.core;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Dish {
    public enum CourseType {
        STARTER, FIRST_COURSE, SECOND_COURSE, DESSERT;
    }

    private String name;
    private Recipe recipe;
    private CourseType type;
    private int preparationTime;
    private LocalDate startDate;
    private LocalDate expireDate;

    public Dish(String name, Recipe recipe, CourseType type) {
        this.name = name;
        this.recipe = recipe;
        this.type = type;
    }

    /**
     *
     * @return true se piatto disponibile al momento della verifica
     */
    public boolean isAvailable() {
        LocalDate today = LocalDate.now();
        return today.isAfter(startDate) && today.isBefore(expireDate);
    }

    /**
     *
     * @return carico di lavoro per porzione del piatto
     */
    public int getWorkload() {
        return recipe.getPortionWorkload();
    }

    @Override
    public String toString() {
        var startDateString = startDate.format(DateTimeFormatter.ofPattern("dd/MM/yy"));
        var expireDateString = expireDate.format(DateTimeFormatter.ofPattern("dd/MM/yy"));

        return "Nome: " + name + "\n\tTipo: " + type.toString() + "\n\tTempo di preparazione: " + preparationTime
        + "\n\tValido dal " + startDateString + " al " + expireDateString;
    }
}
