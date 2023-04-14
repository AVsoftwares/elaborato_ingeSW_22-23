package it.unibs.core;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public abstract class Menu {

    public enum MenuType {
        DEFAULT, LACARTE, THEMATIC;
    }

    public List<Dish> dishList = new ArrayList<>();
    public MenuType menuType = MenuType.DEFAULT;
    public String menuName;
    public LocalDateTime creationTimeStamp;
    public int numDishes = 0;

    public Menu(String name) {
        this.menuName = name;
        this.creationTimeStamp = LocalDateTime.now();
    }

    public void addDish(Dish d) {
        if (dishList.contains(d)) {
            System.out.println("Piatto omonimo già presente in " + menuName);
        } else {
            dishList.add(d);
        }
    }

    public void showDishes() {
        for (Dish d : dishList) {
            System.out.println(d.getName());
        }
    }
}
