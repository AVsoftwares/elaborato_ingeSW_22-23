package it.unibs.ui.storekeeper.commands;

import it.unibs.core.StoreRegister;
import it.unibs.ui.Command;
import it.unibs.ui.InputManager;
import it.unibs.ui.storekeeper.StorekeeperView;

public class RemoveExpiredProductsCommand implements Command {

    private final StorekeeperView view;
    private final StoreRegister storeRegister;

    public RemoveExpiredProductsCommand(StorekeeperView view, StoreRegister storeRegister) {
        this.view = view;
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
