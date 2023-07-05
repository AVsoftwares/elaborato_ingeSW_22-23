package it.unibs.core;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class Menu {
    /**
     * Identifica univocamente il menu
     */
    private final String name;
    private final List<Dish> dishes;

    public Menu(String name, List<Dish> dishes) {
        this.name = name;
        this.dishes = dishes;
    }

    /**
     * @param dish piatto da aggiungere al menu
     */
    public boolean addDish(Dish dish) {
        if (dishes.contains(dish)) {
            return false;
        }
        return dishes.add(dish);
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
