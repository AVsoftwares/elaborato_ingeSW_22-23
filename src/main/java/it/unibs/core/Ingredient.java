package it.unibs.core;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ingredient {
    private String name;
    private float amount;
    private String unit;

    public Ingredient(String name, float amount, String unit) {
        this.name = name;
        this.amount = amount;
        this.unit = unit;
    }
}
