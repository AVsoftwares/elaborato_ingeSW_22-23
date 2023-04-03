package it.unibs.core;

public class Ingredient {
    private String name;
    private int amount;
    private String unitMeasurement;

    public Ingredient(String name) {
        this.name = name;
    }

    public void setUnitMeasurement(String unit){
        this.unitMeasurement = unit;
    }

    public String getUnitMeasurement(String unit){
        return this.unitMeasurement;
    }

    public void setAmount(int amount){
        this.amount = amount;
    }

    public int getAmount(){
        return this.amount;
    }


    public void setName(String n){
        this.name = n;
    }

    public String getName(){
        return this.name;
    }
}
