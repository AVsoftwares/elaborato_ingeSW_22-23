package it.unibs.controller.storekeeper;

import it.unibs.core.ShoppingList;
import it.unibs.core.unit.Quantity;
import it.unibs.ui.Command;
import it.unibs.ui.storekeeper.StorekeeperView;

import java.util.Map;

public class ViewShoppingListCommand implements Command {

    private final StorekeeperView view;
    private final ShoppingList shoppingList;

    public ViewShoppingListCommand(StorekeeperView view, ShoppingList shoppingList) {
        this.view = view;
        this.shoppingList = shoppingList;
    }

    @Override
    public void execute() {
        final Map<String, Quantity> products = shoppingList.getProducts();

        if (products.isEmpty()) {
            view.printEmptyList();
        } else {
            view.printShoppingList(products);
        }
    }
}
