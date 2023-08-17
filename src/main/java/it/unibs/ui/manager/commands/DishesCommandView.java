package it.unibs.ui.manager.commands;

import it.unibs.core.Dish;

import java.util.Set;

public class DishesCommandView {
    public static final String DISH_LIST_INITIALIZED = "La lista di piatti è già stata inizializzata.";
    public static final String DISH_ALREADY_PRESENT = "Un piatto omonimo è già presente.";
    public static final String PERIOD_NOT_VALID = "Il periodo inserito non è valido.";
    public static final String NO_DISHES_SAVED = "Non sono memorizzati piatti.";

    public void printDishListInitialized(){
        System.out.println(DISH_LIST_INITIALIZED);
    }

    public void printDishPresent(){
        System.out.println(DISH_ALREADY_PRESENT);
    }

    public void printPeriodNotValid(){
        System.out.println(PERIOD_NOT_VALID);
    }

    public void printNoSavedDishes(){
        System.out.println(NO_DISHES_SAVED);
    }

    public void printDishes(Set<Dish> dishes) {

        if (dishes.isEmpty()) {
            this.printNoSavedDishes();
            //System.out.println(NO_DISHES_SAVED);
        } else {
            //dishView.printDishes(dishes);
            dishes.forEach(System.out::println);
        }
    }


}
