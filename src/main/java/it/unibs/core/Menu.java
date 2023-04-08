package it.unibs.core;

import java.time.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Menu {
    public List<Dish> dishList = new ArrayList<>();
    public String menuType = "Default";
    public String menuName;
    public LocalDateTime creationTimeStamp;
    public int numDishes = 0;

    public Menu(String name) {
        this.menuName = name;
        this.creationTimeStamp = LocalDateTime.now();
    }

    public void setExpireDate(LocalDateTime startDate, int durationDays) {
    }

    ;

    public String getMenuType() {
        return menuType;
    }

    ;

    public LocalDateTime getCreationTimeStamp() {
        return creationTimeStamp;
    }

    public void addDish(Dish d) {
        if (dishList.contains(d)) {
            System.out.println("Piatto omonimo già presente in " + menuName);
        } else {
            dishList.add(d);
        }
    }

    public void showDishes() {
        for (Dish d: dishList) {
            System.out.println(d.getName());
        }
    }
}
