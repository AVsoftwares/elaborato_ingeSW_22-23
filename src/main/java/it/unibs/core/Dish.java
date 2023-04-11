package it.unibs.core;

import java.time.LocalDateTime;

public class Dish {
    private final String name;
    private int preparationTime;

    private Recipe r;
    private LocalDateTime startDate;
    private LocalDateTime expireDate;

    private String type; //antipasto, primo, secondo... meglio usare una Enum?
    private int durationDays=0;

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

    private void setStartDate(int year, int month, int day){
        //piatto disponibile dalla mezzanotte
        this.startDate = LocalDateTime.of(year, month, day, 0, 0);
    }

    private void setStartDate(int year, int month, int day, int hour, int min){
        //piatto disponibile da orario indicato da gestore
        this.startDate = LocalDateTime.of(year, month, day, hour, min);
    }

    private void setDurationDays(int days){
        //0 MAI valido
        //-1 SEMPRE valido
        this.durationDays = days;
    }

    public void setExpireDate(LocalDateTime startDate, int durationDays){

        if(durationDays != -1 || durationDays != 0) {
            expireDate = startDate.plusDays(durationDays);
        }
    }

    public LocalDateTime getExpireDate(){
        return expireDate;
    }

    public void getValidity() {
        if (durationDays == -1) {
            System.out.println("Piatto: " + name + " SEMPRE disponibile.");
        } else if (durationDays == 0) {
            System.out.println("Piatto" + name + "MAI disponibile.");
        } else {
            System.out.println("Piatto disponibile fino al " + expireDate);
        }
    }
}
