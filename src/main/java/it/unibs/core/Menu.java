package it.unibs.core;

import java.time.LocalDate;

public abstract class Menu {
    //quanti piatti in un menu massimo
    public int DishesNum = 300;
    //listaPiatti
    public Dish[] dishList = new Dish[DishesNum];
    public String menuType= "Default";
    public String menuName;

    public Menu(String name){
        this.menuName = name;
    }

    //setPeriodo Validità
    public void setValidity(LocalDate date, int numDays){};

    public String getMenuType(){

        return menuType;
    };



}
