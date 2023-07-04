package it.unibs.core;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ingredient extends Product {
    public Ingredient(String name) {
        super(name, null, null);
    }

    @Override
    public String toString() {
        return super.getName();
    }
}
