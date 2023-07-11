package it.unibs.core;

import it.unibs.core.unit.Quantity;

import java.util.Map;

/**
 * Interfaccia che fornisce metodi per oggetti ordinabili nel ristorante
 */
public interface Orderable {
    float getWorkload();

    Map<? extends Product, Quantity> getProductsQuantity();
}
