package it.unibs.controller.storekeeper;

import it.unibs.core.Product;
import it.unibs.core.StoreRegister;
import it.unibs.ui.Command;
import it.unibs.ui.InputManager;
import it.unibs.ui.storekeeper.StorekeeperView;

import java.util.List;

public class RemoveProductCommand implements Command {

    private final StorekeeperView view;
    private final StoreRegister storeRegister;

    public RemoveProductCommand(StorekeeperView view, StoreRegister storeRegister) {
        this.view = view;
        this.storeRegister = storeRegister;
    }

    @Override
    public void execute() {
        final String productName = InputManager.readString("Nome: ");

        if (storeRegister.isAvailable(productName)) {
            final List<Product> matchingProducts = storeRegister.getProductsByName(productName);

            for (int i = 0; i < matchingProducts.size(); i++) {
                view.printMatchingProduct(i, matchingProducts.get(i));
            }

            final int choice = InputManager.readInt("Prodotto da rimuovere: ", 0, matchingProducts.size());
            storeRegister.remove(matchingProducts.get(choice));
        } else {
            view.printNotFound(productName);
        }
    }
}
