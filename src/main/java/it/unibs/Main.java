package it.unibs;

import it.unibs.core.ShoppingList;
import it.unibs.core.StoreRegister;
import it.unibs.core.reservation.ConcreteDateValidationStrategy;
import it.unibs.core.reservation.ReservationService;
import it.unibs.ui.BaseMenu;
import it.unibs.ui.manager.ManagerView;
import it.unibs.ui.reservationOfficer.ReservationOfficerView;
import it.unibs.ui.storekeeper.StorekeeperView;

import java.time.Clock;
import java.util.ArrayList;

public class Main {
    private static final String MENU_TITLE = "Main Menu";
    private static final String MSG_MANAGER_LOGIN = "Area gestore";
    private static final String MSG_BOOKING_OFFICER_LOGIN = "Area addetto alle prenotazioni";
    private static final String MSG_WAREHOUSE_MAN_LOGIN = "Area magazziniere";

    public static void main(String[] args) {
        Clock clock = Clock.systemDefaultZone();

        ReservationService reservationService = new ReservationService(new ConcreteDateValidationStrategy());
        StoreRegister storeRegister = new StoreRegister(new ArrayList<>(), clock);
        ShoppingList shoppingList = new ShoppingList(storeRegister, reservationService);

        final BaseMenu mainMenu = new BaseMenu(MENU_TITLE, true);
        mainMenu.addEntry(MSG_MANAGER_LOGIN, () -> new ManagerView().run());
        mainMenu.addEntry(MSG_BOOKING_OFFICER_LOGIN, () -> new ReservationOfficerView(reservationService).run());
        mainMenu.addEntry(MSG_WAREHOUSE_MAN_LOGIN, () -> new StorekeeperView(storeRegister, shoppingList).run());
        mainMenu.run();
    }
}
