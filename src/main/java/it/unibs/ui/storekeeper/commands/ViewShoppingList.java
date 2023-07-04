package it.unibs.ui.storekeeper.commands;

import it.unibs.core.ShoppingList;
import it.unibs.ui.Command;

public class ViewShoppingList implements Command {

    private final ShoppingList shoppingList;

    public ViewShoppingList(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
    }

    @Override
    public void execute() {

    }
}
