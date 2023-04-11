package it.unibs.core;

import java.time.*;
/*È costituito da un elenco di piatti, fra i quali il cliente può scegliere quelli
che desidera vengano serviti. Il menu alla carta relativo a una certa data è unico e contiene
tutti e soli i piatti che sono disponibili in quella data.*/
public class MenuLaCarte extends Menu{

    public MenuLaCarte(String name) {
        super(name);
        this.menuType = "La Carte";
    }
    @Override
    public void showDishes(){
        super.showDishes();
    }

    @Override
    public String getMenuType() {
        return super.getMenuType();
    }
    @Override
    public LocalDateTime getCreationTimeStamp() {
        return super.getCreationTimeStamp();
    }

    @Override
    public void addDish(Dish d) {
        super.addDish(d);
    }

    public void showAvailableDishes(){
        for (int i = 0; i < numDishes; i++) {

            if(dishList[i].getExpireDate().isAfter(LocalDateTime.now())){ //TODO: checkare
                System.out.println(dishList[i].getName());
            }
        }
    }
}
