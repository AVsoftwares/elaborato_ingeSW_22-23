package it.unibs.ui.storekeeper.commands;

import it.unibs.core.Product;
import it.unibs.core.StoreRegister;
import it.unibs.ui.Command;
import it.unibs.ui.InputManager;

import java.util.List;

public class RemoveProductCommand implements Command {

    private final StoreRegister storeRegister;

    public RemoveProductCommand(StoreRegister storeRegister) {
        this.storeRegister = storeRegister;
    }

    @Override
    public void execute() {
        final String productName = InputManager.readString("Nome: ");

        if (storeRegister.isAvailable(productName)) {
            final List<Product> matchingProducts = storeRegister.getProductsByName(productName);

            for (int i = 0; i < matchingProducts.size(); i++) {
                System.out.println("\t- " + i + "\t" + matchingProducts.get(i));
            }

            final int choice = InputManager.readInt("Prodotto da rimuovere: ", 0, matchingProducts.size());
            storeRegister.remove(matchingProducts.get(choice));
        } else {
            System.out.println("Il prodotto non è presente nel magazzino.");
        }
    }
}
