package it.unibs.ui.storekeeper;

import it.unibs.controller.storekeeper.*;
import it.unibs.core.Product;
import it.unibs.core.ShoppingList;
import it.unibs.core.StoreRegister;
import it.unibs.ui.BaseMenu;

import java.util.List;

public class StorekeeperView extends BaseMenu {

    public static final String ERR_INVALID_QUANTITY = """
            La quantità inserita non è valida.
            Deve essere nel formato: quantity [prefix unit]
            Le unità di misura accettate sono (l)itri e (g)rammi, se omessa si considerano le unità""";
    public static final String ERR_NOT_FOUND = "Nessuna corrispondenza trovata per:";
    public static final String ERR_EMPTY_LIST = "La lista è vuota";
    public static final String MSG_AVAILABLE_PRODUCTS = "Sono presenti i seguenti prodotti:";
    private static final String MENU_NAME = "Menu magazziniere";
    private static final String MSG_ADD_PRODUCT = "Aggiungi prodotto nel registro del magazzino";
    private static final String MSG_REMOVE_PRODUCT = "Rimuovi prodotto dal registro del magazzino";
    private static final String MSG_REMOVE_EXPIRED_PRODUCTS = "Rimuovi prodotti scaduti dal registro del magazzino";
    private static final String MSG_VIEW_STORE_REGISTER = "Visualizza registro del magazzino";
    private static final String MSG_VIEW_SHOPPING_LIST = "Visualizza la lista della spesa";
    private static final String MSG_PRODUCT_SUPPLY = "Acquista prodotti";

    public StorekeeperView(StoreRegister storeRegister, ShoppingList shoppingList) {
        super(MENU_NAME);
        addEntry(MSG_ADD_PRODUCT, new AddProductCommand(this, storeRegister));
        addEntry(MSG_REMOVE_PRODUCT, new RemoveProductCommand(this, storeRegister));
        addEntry(MSG_REMOVE_EXPIRED_PRODUCTS, new RemoveExpiredProductsCommand(this, storeRegister));
        addEntry(MSG_VIEW_STORE_REGISTER, new ViewStoreRegisterCommand(this, storeRegister));
        addEntry(MSG_VIEW_SHOPPING_LIST, new ViewShoppingList(this, shoppingList));
        addEntry(MSG_PRODUCT_SUPPLY, new ProductSupplyCommand(this, storeRegister, shoppingList));
    }

    public void printInvalidQuantity() {
        System.out.println(ERR_INVALID_QUANTITY);
    }

    public void printNotFound(String name) {
        System.out.println(ERR_NOT_FOUND + name);
    }

    public void printEmptyList() {
        System.out.println(ERR_EMPTY_LIST);
    }

    public void printProducts(List<Product> products) {
        System.out.println(MSG_AVAILABLE_PRODUCTS);
        products.forEach(System.out::println);
    }
}
