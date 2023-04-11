package it.unibs.core;

import java.time.LocalDate;
import java.time.LocalTime;

public class MenuLaCarte extends Menu{
    public LocalDate validityPeriod;
    private LocalDate creationDate;
    private int durationDays = 0;

    public MenuLaCarte(String name) {
        super(name);
    }

    private void setDurationDays(int days){
        this.durationDays = days;
    }

    private void setCreationDate(){

        this.creationDate = LocalTime.now();
    }
    private void setStartDate(LocalDate d){

    }
    @Override
    public void setValidity(LocalDate startDate, int durationDays) {
        //controlli su data passata

        setStartDate();

        //set durata in giorni
        setDurationDays(durationDays);
    }

    @Override
    public void setMenuType() {
        this.menuType = "La Carte";
    }

    @Override
    public String getMenuType() {

        return super.getMenuType();
    }
}
