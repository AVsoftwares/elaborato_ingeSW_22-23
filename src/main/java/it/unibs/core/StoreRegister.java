package it.unibs.core;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Tiene traccia dei prodotti in magazzino, vi interagisce il magazziniere
 */
public class StoreRegister {

    /**
     * Lista di prodotti presenti in magazzino
     */
    private final List<Product> products = new ArrayList<>();

    /**
     * Verifica se un prodotto è presente in magazzino
     * @param name nome del prodotto
     * @return true se in magazzino è presente un prodotto con tale nome ed in quantità sufficiente, false altrimenti
     */
    public boolean isAvailable(String name) {
        return products.stream().anyMatch(product -> product.getName().equalsIgnoreCase(name) && product.isAvailable());
    }

    /**
     * Ritorna una lista di prodotti con nome uguale al nome in input
     * @param name nome dei prodotti da cercare
     * @return lista di prodotti con nome corrispondente all'input
     */
    public List<Product> getProductsByName(String name) {
        return products.stream()
                .filter(product -> product.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    /**
     * Aggiunge un prodotto al magazzino, se già presente aggiorna la quantità
     * @param product prodotto da aggiungere
     */
    public void add(Product product) {
        final int index = products.indexOf(product);
        if (index >= 0) {
            products.get(index).getQuantity().add(product.getQuantity());
            return;
        }
        products.add(product);
    }

    /**
     * Rimuove un prodotto dal magazzino
     * @param product prodotto da rimuovere
     */
    public void remove(Product product) {
        products.remove(product);
    }

    /**
     * Rimuove i prodotti scaduti dal magazzino
     */
    public void removeExpiredProducts() {
        products.removeIf(product -> !product.isValid());
    }

    public List<Product> getProducts() {
        return products;
    }
}


