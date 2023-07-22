package it.unibs;

import it.unibs.core.ReservationService;
import it.unibs.core.Restaurant;
import it.unibs.core.ShoppingList;
import it.unibs.core.StoreRegister;
import it.unibs.ui.Menu;
import it.unibs.ui.manager.ManagerMenu;
import it.unibs.ui.reservationOfficer.ReservationOfficerMenu;
import it.unibs.ui.storekeeper.StorekeeperMenu;

import java.time.Clock;
import java.util.ArrayList;

public class Main {
    private static final String MENU_TITLE = "Main Menu";
    private static final String MSG_MANAGER_LOGIN = "Area gestore";
    private static final String MSG_BOOKING_OFFICER_LOGIN = "Area addetto alle prenotazioni";
    private static final String MSG_WAREHOUSE_MAN_LOGIN = "Area magazziniere";

    public static void main(String[] args) {
        Clock clock = Clock.systemDefaultZone();

        Restaurant restaurant = new Restaurant();
        ReservationService reservationService = new ReservationService();
        StoreRegister storeRegister = new StoreRegister(new ArrayList<>(), clock);
        ShoppingList shoppingList = new ShoppingList(restaurant, storeRegister, reservationService);

        final Menu mainMenu = new Menu(MENU_TITLE, true);
        mainMenu.addEntry(MSG_MANAGER_LOGIN, () -> new ManagerMenu(restaurant).run());
        mainMenu.addEntry(MSG_BOOKING_OFFICER_LOGIN, () -> new ReservationOfficerMenu(restaurant, reservationService).run());
        mainMenu.addEntry(MSG_WAREHOUSE_MAN_LOGIN, () -> new StorekeeperMenu(storeRegister, shoppingList).run());
        mainMenu.run();
    }
}
