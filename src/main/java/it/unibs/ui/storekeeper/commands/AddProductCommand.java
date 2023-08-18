package it.unibs.ui.storekeeper.commands;

import it.unibs.core.Product;
import it.unibs.core.StoreRegister;
import it.unibs.core.unit.Quantity;
import it.unibs.ui.Command;
import it.unibs.ui.InputManager;
import it.unibs.ui.storekeeper.StorekeeperView;

import java.time.LocalDate;
import java.util.Optional;

public class AddProductCommand implements Command {
    private final StorekeeperView view;
    private final StoreRegister storeRegister;

    public AddProductCommand(StorekeeperView view, StoreRegister storeRegister) {
        this.view = view;
        this.storeRegister = storeRegister;
    }

    @Override
    public void execute() {
        final String productName = InputManager.readString("Nome: ");
        final LocalDate expirationDate = InputManager.readDate("Data di scadenza: ",
                InputManager.DEFAULT_DATE_FORMATTER_PATTERN);

        final Optional<Quantity> quantity = Quantity.fromString(InputManager.readString("Quantità (es. 10 kg): "));

        if (quantity.isPresent()) {
            storeRegister.add(new Product(productName, expirationDate, quantity.get()));
        } else {
            view.printInvalidQuantity();
        }
    }
}
