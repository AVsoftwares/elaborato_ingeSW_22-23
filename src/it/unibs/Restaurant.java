package it.unibs;


public class Restaurant {
    private int coversNumber=0;
    private int avgBeverageEach=0;
    private int avgExtraEach=0;
    private int individualWorkLoad=0;

    private String restaurantName;

    public Restaurant(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public void initializeDataConfig(File f){
        //Gestore inizializza da file i parametri avgBeverageEach, coversNumber, avgExtraEach..

    }
    //magari il metodo sopra e questo sotto lo mettiamo in una classe di utility tipo RestaurantUtility
    private void setRestaurantMealWorkLoad(int IndividualWorkLoad, int coversNumber){
        //prodotto del
        //carico di lavoro per persona per il numero complessivo di posti a sedere del ristorante
        //accresciuto del 20%.
    }
    public void showDataConfig(){

    }

    //questi due metodi forse meglio spostarli sul Gestore?
    public void makeMenu(List recipe){

    }

    public void showMenus(){

    }

}
