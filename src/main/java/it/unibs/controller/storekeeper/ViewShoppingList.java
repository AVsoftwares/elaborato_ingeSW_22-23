package it.unibs.controller.storekeeper;

import it.unibs.core.ShoppingList;
import it.unibs.core.unit.Quantity;
import it.unibs.ui.Command;
import it.unibs.ui.storekeeper.StorekeeperView;

import java.util.Map;

public class ViewShoppingList implements Command {

    private final StorekeeperView view;
    private final ShoppingList shoppingList;

    public ViewShoppingList(StorekeeperView view, ShoppingList shoppingList) {
        this.view = view;
        this.shoppingList = shoppingList;
    }

    @Override
    public void execute() {
        final Map<String, Quantity> products = shoppingList.getProducts();

        if (products.isEmpty()) {
            view.printEmptyList();
        } else {
            products.entrySet().forEach(System.out::println);
        }
    }
}
