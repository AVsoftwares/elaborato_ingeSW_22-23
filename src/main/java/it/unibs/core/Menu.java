package it.unibs.core;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Menu {
    private List<Dish> dishes;
    private String name;

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
}
