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
    public int numDishes=0;

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

    public void addDish(Dish d){
        int pos=0;
        //cercare la prima posizione disponibile
        for(int i=0; i< dishList.length; i++){
            if(dishList[i] == null){
                pos = i;
            }
        }
        //controllo che non ci siano piatti con lo stesso nome
        for (int i=0; i<= numDishes-1; i++){
            if(d.getName() == dishList[i].getName()){
                System.out.println("Piatto omonimo già presente in "+ menuName);
            }
            else{
                dishList[pos] = d;
                numDishes++;
            }
        }
    }

    public void showDishes(){
        for (int i=0; i<numDishes; i++){
            System.out.println(dishList[i].getName());
        }
    }
}
