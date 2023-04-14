package it.unibs.core;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ingredient {
    private String name;
    private int amount;
    private String unitMeasurement;

    public Ingredient(String name) {
        this.name = name;
    }
}
