package it.unibs;

import it.unibs.core.Restaurant;
import it.unibs.ui.login.LoginMenu;

public class Main {
    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant();

        new LoginMenu(restaurant).run();
    }
}
