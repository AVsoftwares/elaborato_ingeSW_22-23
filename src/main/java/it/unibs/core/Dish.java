package it.unibs.core;

import java.time.LocalDate;

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

    public boolean isAvailable() {
        LocalDate today = LocalDate.now();
        return today.isAfter(startDate) && today.isBefore(expireDate);
    }

    public int getWorkload() {
        return recipe.getPortionWorkload();
    }

    @Override
    public String toString() {
        return "Nome: " + name + "\n\tTipo: " + type.toString() + "\n\tTempo di preparazione: " + preparationTime
        + "\n\tValido dal " + startDate.toString() + " al " + expireDate.toString();
    }
}
