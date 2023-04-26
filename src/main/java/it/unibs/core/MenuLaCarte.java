package it.unibs.core;

import lombok.Getter;
import lombok.Setter;

/**
 * MenuLaCarte è costituito da un elenco di piatti, fra i quali il cliente può
 * scegliere quelli
 * che desidera vengano serviti. Il menu alla carta relativo a una certa data è
 * unico e contiene
 * tutti e soli i piatti che sono disponibili in quella data.
 */
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
