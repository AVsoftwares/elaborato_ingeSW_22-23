package it.unibs.core;

import java.time.*;

/**
 * MenuLaCarte è costituito da un elenco di piatti, fra i quali il cliente può scegliere quelli
 * che desidera vengano serviti. Il menu alla carta relativo a una certa data è unico e contiene
 * tutti e soli i piatti che sono disponibili in quella data.
 */
public class MenuLaCarte extends Menu {

    public MenuLaCarte(String name) {
        super(name);
        this.menuType = "La Carte";
    }

    public void showAvailableDishes() {
        for (Dish d: dishList) {
            if (d.isAvailable()) {
                System.out.println(d.getName());
            }
        }
    }
}
