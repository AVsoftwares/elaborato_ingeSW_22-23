package it.unibs.core;

/**
 * Rappresenta un ingrediente, viene aggregato nelle ricette
 */
public class Ingredient extends Product {
    public Ingredient(String name) {
        super(name, null, null);
    }

    @Override
    public String toString() {
        return super.getName();
    }
}
