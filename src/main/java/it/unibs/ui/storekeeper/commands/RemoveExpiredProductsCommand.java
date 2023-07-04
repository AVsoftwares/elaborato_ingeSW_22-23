package it.unibs.ui.storekeeper.commands;

import it.unibs.core.StoreRegister;
import it.unibs.ui.Command;
import it.unibs.ui.InputManager;

public class RemoveExpiredProductsCommand implements Command {

    private final StoreRegister storeRegister;

    public RemoveExpiredProductsCommand(StoreRegister storeRegister) {
        this.storeRegister = storeRegister;
    }

    @Override
    public void execute() {
        final boolean doRemoveExpired = InputManager.readYesOrNo("Procedere alla rimozione automatica dei prodotti scaduti? (y)es/(n)o: ");

        if (doRemoveExpired) {
            storeRegister.removeExpiredProducts();
        }
    }
}
