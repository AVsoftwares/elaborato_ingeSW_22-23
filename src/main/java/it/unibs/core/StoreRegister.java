package it.unibs.core;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StoreRegister {

    private final List<Product> products = new ArrayList<>();

    public boolean isAvailable(String name) {
        return products.stream().anyMatch(product -> product.getName().equalsIgnoreCase(name) && product.isAvailable());
    }

    public List<Product> getProductsByName(String name) {
        return products.stream()
                .filter(product -> product.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    public void add(Product product) {
        final int index = products.indexOf(product);
        if (index >= 0) {
            products.get(index).getQuantity().add(product.getQuantity());
            return;
        }
        products.add(product);
    }

    public void remove(Product product) {
        products.remove(product);
    }

    public void removeExpiredProducts() {
        products.removeIf(Product::isExpired);
    }

    public List<Product> getProducts() {
        return products;
    }
}


