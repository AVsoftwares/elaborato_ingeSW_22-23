package it.unibs.core;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Menu {
    private List<Dish> dishes = new ArrayList<>();
    private String name;

    public Menu(String name) {
        this.name = name;
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
