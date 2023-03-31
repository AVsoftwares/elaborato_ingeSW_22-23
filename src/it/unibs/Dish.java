package it.unibs;

public class Dish {
    private String name;
    private int preparationTime;

    private String type; //antipasto, primo, secondo... meglio usare una Enum?

    //periodo validita come lo gestiamo? per stagioni con una enum?
    //getters e setters?
    public Dish(String name){
        this.name = name;
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
}
