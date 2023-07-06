package it.unibs.ui.storekeeper.commands;

import it.unibs.core.Product;
import it.unibs.core.ShoppingList;
import it.unibs.core.StoreRegister;
import it.unibs.core.unit.Quantity;
import it.unibs.ui.Command;
import it.unibs.ui.InputManager;

import java.time.LocalDate;
import java.util.Optional;

public class ProductSupplyCommand implements Command {

    private final StoreRegister storeRegister;
    private final ShoppingList shoppingList;

    public ProductSupplyCommand(StoreRegister storeRegister, ShoppingList shoppingList) {
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
                System.out.println("""
                        La quantità inserita non è valida.
                        Deve essere nel formato: quantity [prefix unit]
                        Le unità di misura accettate sono (l)itri e (g)rammi, se omessa si considerano le unità""");
            }
        } else {
            System.out.println("Il prodotto non è presente nella lista della spesa, non è necessario acquistarlo.");
        }
    }
}
