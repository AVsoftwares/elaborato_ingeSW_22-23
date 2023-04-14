package it.unibs.core;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Dish {
    public enum CourseType {
        STARTER, FIRST_COURSE, SECOND_COURSE, DESSERT;
    }

    private final String name;
    private int preparationTime;
    private Recipe recipe;
    private CourseType type;
    private LocalDateTime startDate;
    private LocalDateTime expireDate;

    public Dish(String name, Recipe recipe, CourseType type) {
        this.name = name;
        this.recipe = recipe;
        this.type = type;
    }

    public boolean isAvailable() {
        LocalDateTime today = LocalDateTime.now();
        return today.isAfter(startDate) && today.isBefore(expireDate);
    }

    public int getDishPortionWorkLoad() {
        return recipe.getPortionWorkLoad();
    }
}
