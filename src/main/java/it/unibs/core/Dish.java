package it.unibs.core;

public class Dish {
    private final String name;
    private int preparationTime;

    private Recipe r;

    private String type; //antipasto, primo, secondo... meglio usare una Enum?

    //periodo validita come lo gestiamo? per stagioni con una enum?
    //getters e setters?
    public Dish(String name, Recipe r){

        this.name = name;
        this.r = r;
    }

    public void setPreparationTime(int minutes){
        this.preparationTime =minutes;
    }

    public int getPreparationTime(){
        return this.preparationTime;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getDishPortionWorkLoad(){
        return r.getPortionWorkLoad();
    }
}
