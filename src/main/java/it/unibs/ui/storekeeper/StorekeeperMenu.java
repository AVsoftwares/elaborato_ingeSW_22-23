package it.unibs.ui.storekeeper;

import it.unibs.core.ShoppingList;
import it.unibs.core.StoreRegister;
import it.unibs.ui.Menu;
import it.unibs.ui.storekeeper.commands.AddProductCommand;
import it.unibs.ui.storekeeper.commands.RemoveExpiredProductsCommand;
import it.unibs.ui.storekeeper.commands.RemoveProductCommand;
import it.unibs.ui.storekeeper.commands.ViewShoppingList;

public class StorekeeperMenu extends Menu {

    private static final String MENU_NAME = "Menu magazziniere";
    private static final String MSG_ADD_PRODUCT = "Aggiungi prodotto nel registro del magazzino";
    private static final String MSG_REMOVE_PRODUCT = "Rimuovi prodotto dal registro del magazzino";
    private static final String MSG_REMOVE_EXPIRED_PRODUCTS = "Rimuovi prodotti scaduti dal registro del magazzino";
    private static final String MSG_VIEW_SHOPPING_LIST = "Visualizza la lista della spesa";

    public StorekeeperMenu(StoreRegister storeRegister, ShoppingList shoppingList) {
        super(MENU_NAME);
        addEntry(MSG_ADD_PRODUCT, new AddProductCommand(storeRegister));
        addEntry(MSG_REMOVE_PRODUCT, new RemoveProductCommand(storeRegister));
        addEntry(MSG_REMOVE_EXPIRED_PRODUCTS, new RemoveExpiredProductsCommand(storeRegister));
        addEntry(MSG_VIEW_SHOPPING_LIST, new ViewShoppingList(shoppingList));
    }
}
