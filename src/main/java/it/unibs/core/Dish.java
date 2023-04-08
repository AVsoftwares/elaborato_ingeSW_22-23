package it.unibs.core;

import java.time.LocalDateTime;

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

    private int durationDays = 0;

    public Dish(String name, Recipe recipe, CourseType type) {
        this.name = name;
        this.recipe = recipe;
        this.type = type;
    }

    public void setPreparationTime(int minutes) {
        this.preparationTime = minutes;
    }

    public int getPreparationTime() {
        return this.preparationTime;
    }

    public void setType(CourseType type) {
        this.type = type;
    }

    public boolean isAvailable() {
        LocalDateTime today = LocalDateTime.now();
        return today.isAfter(startDate) && today.isBefore(expireDate);
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

    public CourseType getType() {
        return type;
    }

    public int getDishPortionWorkLoad() {
        return recipe.getPortionWorkLoad();
    }

    private void setStartDate(int year, int month, int day, int hour, int min) {
        this.startDate = LocalDateTime.of(year, month, day, hour, min);
    }

    private void setStartDate(int year, int month, int day) {
        this.setStartDate(year, month, day, 0, 0);
    }

    private void setDurationDays(int days) {
        //0 MAI valido
        //-1 SEMPRE valido
        this.durationDays = days;
    }

    public void setExpireDate(LocalDateTime startDate, int durationDays) {
        if (durationDays != -1 || durationDays != 0) {
            expireDate = startDate.plusDays(durationDays);
        }
    }

    public LocalDateTime getExpireDate() {
        return expireDate;
    }

    public void getValidity() {
        if (durationDays == -1) {
            System.out.println("Piatto: " + name + " SEMPRE disponibile.");
        } else if (durationDays == 0) {
            System.out.println("Piatto" + name + "MAI disponibile.");
        } else {
            System.out.println("Piatto disponibile fino al " + expireDate);
        }
    }
}
