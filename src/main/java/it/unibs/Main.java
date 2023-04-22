package it.unibs;

import it.unibs.core.Restaurant;
import it.unibs.ui.Menu;
import it.unibs.ui.manager.ManagerMenu;

public class Main {
    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant("Un bel ristorante");

        Menu managerMenu = new ManagerMenu("Manager Menu", restaurant, true);

        managerMenu.run();
    }
}
