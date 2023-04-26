package it.unibs.core;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Menu {
    private List<Dish> dishes = new ArrayList<>();
    private String name;

    public Menu(String name) {
        this.name = name;
    }

    public void addDish(Dish d) {
        if (dishes.contains(d)) {
            System.out.println("Piatto omonimo già presente in " + name);
        } else {
            dishes.add(d);
        }
    }

    public void showDishes() {
        for (Dish d : dishes) {
            System.out.println(d.getName());
        }
    }
}
