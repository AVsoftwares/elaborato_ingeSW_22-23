package it.unibs.core;

import it.unibs.core.unit.Quantity;

import java.util.Map;

public class StoreRegister {
    private final Map<Product, Quantity> products;

    public StoreRegister(Map<Product, Quantity> products) {
        this.products = products;
    }

    public boolean isAvailable(String name) {
        return products.keySet().stream().anyMatch(p -> p.getName().equalsIgnoreCase(name));
    }
}


