package it.unibs.ui.storekeeper.commands;

import it.unibs.core.Product;
import it.unibs.core.ShoppingList;
import it.unibs.core.StoreRegister;
import it.unibs.core.unit.Quantity;
import it.unibs.ui.Command;
import it.unibs.ui.InputManager;
import it.unibs.ui.storekeeper.StorekeeperView;

import java.time.LocalDate;
import java.util.Optional;

public class ProductSupplyCommand implements Command {
    private final StorekeeperView view;
    private final StoreRegister storeRegister;
    private final ShoppingList shoppingList;

    public ProductSupplyCommand(StorekeeperView view, StoreRegister storeRegister, ShoppingList shoppingList) {
        this.view = view;
        this.storeRegister = storeRegister;
        this.shoppingList = shoppingList;
    }

    @Override
    public void execute() {
        final String productName = InputManager.readString("Nome del prodotto da acquistare: ");

        if (shoppingList.contains(productName)) {
            final LocalDate expiration = InputManager.readDate("Data di scadenza: ", InputManager.DEFAULT_DATE_FORMATTER_PATTERN);
            final Optional<Quantity> quantity = Quantity.fromString(InputManager.readString("Quantità (es. 10 kg): "));

            if (quantity.isPresent()) {
                storeRegister.add(new Product(productName, expiration, quantity.get()));
            } else {
                view.printInvalidQuantity();
            }
        } else {
            view.printNotFound(productName);
        }
    }
}
