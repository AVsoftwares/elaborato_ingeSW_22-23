package it.unibs.core;

import java.time.*;

public abstract class Menu {
    //quanti piatti in un menu massimo
    public int DishesNum = 300;
    //listaPiatti
    public Dish[] dishList = new Dish[DishesNum];
    public String menuType= "Default";
    public String menuName;
    public LocalDateTime creationTimeStamp;

    public Menu(String name){
        this.menuName = name;
        this.creationTimeStamp = LocalDateTime.now();
    }

    public void setExpireDate(LocalDateTime startDate, int durationDays){};

    public String getMenuType(){
        return menuType;
    };

    public LocalDateTime getCreationTimeStamp(){
        return creationTimeStamp;
    }
}
