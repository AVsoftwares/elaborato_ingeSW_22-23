package it.unibs.core;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuLaCarte extends Menu {

    public MenuLaCarte(String name) {
        super(name);
    }

    @Override
    public void showDishes() {
        for (Dish d : super.getDishes()) {
            if (d.isAvailable()) {
                System.out.println(d.getName());
            }
        }
    }
}
