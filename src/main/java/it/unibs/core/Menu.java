package it.unibs.core;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Menu {
    /**
     * Identifica univocamente il menu
     */
    private final String name;
    private final List<Dish> dishes;

    public Menu(String name, List<Dish> dishes) {
        this.name = Objects.requireNonNull(name);
        this.dishes = Objects.requireNonNull(dishes);
    }

    /**
     * @param dish piatto da aggiungere al menu
     */
    public boolean add(Dish dish) {
        if (dishes.contains(dish)) {
            return false;
        }
        return dishes.add(dish);
    }

    public List<Dish> getDishes() {
        return Collections.unmodifiableList(dishes);
    }

    @Override
    public String toString() {
        return name;
    }

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
