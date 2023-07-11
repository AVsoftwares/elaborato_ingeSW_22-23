package it.unibs.core;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Rappresenta un menu ordinabile al ristorante
 */
public class Menu {
    /**
     * Identifica univocamente il menu
     */
    private final String name;
    /**
     * Lista di piatti che fanno parte del menu
     */
    private final List<Dish> dishes;

    public Menu(String name, List<Dish> dishes) {
        this.name = Objects.requireNonNull(name);
        this.dishes = Objects.requireNonNull(dishes);
    }

    /**
     * Aggiunge un piatto all'elenco di piatti del menu, purché il piatto non sia già presente nel menu
     * @param dish piatto da aggiungere al menu
     * @return true se il piatto è stato aggiunto in quanto non era presente nel menu, false altrimenti
     */
    public boolean add(Dish dish) {
        if (dishes.contains(dish)) {
            return false;
        }
        return dishes.add(dish);
    }

    /**
     * getter che ritorna una lista immutabile di piatti, affinché il client non faccia un uso errato del metodo
     * @return lista immutabile di piatti
     */
    public List<Dish> getImmutableDishes() {
        return Collections.unmodifiableList(dishes);
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * Metodo per verificare se due Menu sono equivalenti
     * @param o oggetto rispetto al quale viene verificata l'equivalenza
     * @return true se i due oggetti sono riferimenti alla stessa istanza o se entrambi sono istanze di
     * Menu ed hanno nome uguale secondo {@link String#equals(Object) String.equals}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return Objects.equals(name, menu.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
