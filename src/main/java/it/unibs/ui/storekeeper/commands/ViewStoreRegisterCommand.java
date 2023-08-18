package it.unibs.ui.storekeeper.commands;

import it.unibs.core.StoreRegister;
import it.unibs.ui.Command;
import it.unibs.ui.storekeeper.StorekeeperView;

public class ViewStoreRegisterCommand implements Command {

    private final StorekeeperView view;
    private final StoreRegister storeRegister;

    public ViewStoreRegisterCommand(StorekeeperView view, StoreRegister storeRegister) {
        this.view = view;
        this.storeRegister = storeRegister;
    }

    @Override
    public void execute() {
        if (storeRegister.getProducts().isEmpty()) {
            view.printEmptyList();
        } else {
            view.printProducts(storeRegister.getProducts());
        }
    }
}
