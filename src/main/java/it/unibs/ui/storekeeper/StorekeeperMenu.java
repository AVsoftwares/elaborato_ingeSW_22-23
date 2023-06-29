package it.unibs.ui.storekeeper;

import it.unibs.core.Restaurant;
import it.unibs.ui.Menu;
import it.unibs.ui.storekeeper.commands.UpdateStoreRegisterCommand;

public class StorekeeperMenu extends Menu {

    private static final String MENU_NAME = "Menu magazziniere";
    private static final String MSG_UPDATE_STORE_REGISTER = "Aggiorna il registro del magazzino";

    public StorekeeperMenu(Restaurant restaurant) {
        super(MENU_NAME);
    }

    private void initMenuEntries(Restaurant restaurant) {
        addEntry(MSG_UPDATE_STORE_REGISTER, new UpdateStoreRegisterCommand(restaurant));
    }
}
