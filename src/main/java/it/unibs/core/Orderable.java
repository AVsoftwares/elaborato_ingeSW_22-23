package it.unibs.core;

import it.unibs.core.unit.Quantity;

import java.util.Map;

public interface Orderable {
    float getWorkload();

    Map<? extends Product, Quantity> getProductsQuantity();
}
