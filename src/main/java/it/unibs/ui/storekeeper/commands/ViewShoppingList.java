package it.unibs.ui.storekeeper.commands;

import it.unibs.core.ShoppingList;
import it.unibs.core.unit.Quantity;
import it.unibs.ui.Command;

import java.util.Map;

public class ViewShoppingList implements Command {

    private final ShoppingList shoppingList;

    public ViewShoppingList(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
    }

    @Override
    public void execute() {
        final Map<String, Quantity> products = shoppingList.getProducts();

        if (products.isEmpty()) {
            System.out.println("La lista è vuota.");
        } else {
            products.entrySet().forEach(System.out::println);
        }
    }
}
