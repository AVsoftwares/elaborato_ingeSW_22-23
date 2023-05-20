package it.unibs.ui.login;

import it.unibs.core.Restaurant;
import it.unibs.ui.Menu;
import it.unibs.ui.manager.ManagerMenu;

public final class LoginMenu extends Menu {

    private static final String MENU_NAME = "Login";
    private static final String MSG_MANAGER_LOGIN = "Gestore";
    private static final String MSG_BOOKING_OFFICER_LOGIN = "Addetto alle prenotazioni";
    private static final String MSG_WAREHOUSE_MAN_LOGIN = "Magazziniere";

    /**
     * @param restaurant
     */
    public LoginMenu(Restaurant restaurant) {
        super(MENU_NAME, true);
        initMenuEntries(restaurant);
    }

    /**
     * @param restaurant
     */
    private void initMenuEntries(Restaurant restaurant) {
        addEntry(MSG_MANAGER_LOGIN, () -> {
            ManagerMenu menu = new ManagerMenu(restaurant);

            menu.run();
        });
        addEntry(MSG_BOOKING_OFFICER_LOGIN, () -> {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'onSelection'");
        });
        addEntry(MSG_WAREHOUSE_MAN_LOGIN, () -> {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'onSelection'");
        });
    }
}
