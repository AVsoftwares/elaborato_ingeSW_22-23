package it.unibs.core;

import java.time.*;

/*
Menu tematico È stabilito dal gestore e costituito da un elenco di piatti destinati a essere
ordinati dal cliente tutti insieme per una persona. Può non essere disponibile per tutto
l’arco dell’anno. La somma del carico di lavoro di tutti i piatti contenuti, detta carico di
lavoro del menu tematico, deve essere minore o uguale ai 4/3 del carico di lavoro per
persona. Possono coesistere più menu tematici, anche contemporaneamente validi.
* */
public class ThematicMenu extends Menu{

    private String menuName;
    private LocalDateTime creationTimeStamp;
    private LocalDateTime startDate;
    private LocalDateTime expireDate;
    private int durationDays =0;
    private int numDishes = 0;

    private int menuWorkLoad =0;
    @Override

    public ThematicMenu(String name){
        super(name);
        this.menuType = "Thematic";
        this.creationTimeStamp = LocalDateTime.now();
    }

    @Override
    public String getMenuType() {
       return this.getMenuType();
    }

    private void setStartDate(int year, int month, int day){
        //menu disponibile dalla mezzanotte
        this.startDate = LocalDateTime.of(year, month, day, 0, 0);
    }

    private void setStartDate(int year, int month, int day, int hour, int min){
        //menu disponibile da orario indicato da gestore
        this.startDate = LocalDateTime.of(year, month, day, hour, min);
    }

    private void setDurationDays(int days){
        //0 MAI valido
        //-1 SEMPRE valido
        this.durationDays = days;
    }

    private void setExpireDate(LocalDateTime startDate, int durationDays){

        if(durationDays != -1 || durationDays != 0) {
            expireDate = startDate.plusDays(durationDays);
        }
    }
    @Override
    public void getValidity() {
        if(durationDays == -1){
            System.out.println("Menu Tematico: " + menuName + " SEMPRE valido.");
        }
        else if (durationDays == 0){
            System.out.println("Menu Tematico" + menuName + "MAI valido.");
        }
        else{
            System.out.println("Menu Tematico valido fino al "+ expireDate);
        }
    }

    public int getDurationDays(){
        return this.durationDays;
    }

    public LocalDateTime getExpireDate(){
        return this.expireDate;
    }

    public void addDish(Dish d){
        int pos=0;
        //cercare la prima posizione disponibile
        for(int i=0; i< dishList.length; i++){
            if(dishList[i] == null){
                pos = dishList[i];
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

    public void showMenu(){
        for (int i=0; i<numDishes; i++){
            System.out.println(dishList[i].getName());
        }
    }

    private void computePortionWorkLoad(){
        for (int i=0; i<numDishes; i++){
            menuWorkLoad = menuWorkLoad+dishList[i].getDishPortionWorkLoad();
        }
    }
    public int getMenuWorkLoad(){
        this.computePortionWorkLoad();
        return menuWorkLoad;
    }
}
