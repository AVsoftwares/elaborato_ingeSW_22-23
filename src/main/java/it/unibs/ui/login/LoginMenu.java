package it.unibs.ui.login;

import it.unibs.core.Restaurant;
import it.unibs.ui.Menu;

public final class LoginMenu extends Menu {

    private final Restaurant restaurant;

    private static final String MENU_NAME = "Login";
    private static final String MSG_MANAGER_LOGIN = "Gestore";
    private static final String MSG_BOOKING_OFFICER_LOGIN = "Addetto alle prenotazioni";
    private static final String MSG_WAREHOUSE_MAN_LOGIN = "Magazziniere";

    public LoginMenu(Restaurant restaurant) {
        super(MENU_NAME, true);
        this.restaurant = restaurant;
        initMenuEntries();
    }

    private void initMenuEntries() {
        addEntry(MSG_MANAGER_LOGIN, new ManagerLoginCommand(restaurant));
        addEntry(MSG_BOOKING_OFFICER_LOGIN, new BookingOfficerLoginCommand(restaurant));
        addEntry(MSG_WAREHOUSE_MAN_LOGIN, new WarehouseManLoginCommand(restaurant));
    }
}
