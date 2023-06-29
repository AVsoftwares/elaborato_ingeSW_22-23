package it.unibs.core;

import it.unibs.core.unit.Quantity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ingredient extends Product {
    public Ingredient(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return super.getName();
    }
}
