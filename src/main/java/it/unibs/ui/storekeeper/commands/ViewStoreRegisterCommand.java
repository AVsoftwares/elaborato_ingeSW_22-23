package it.unibs.ui.storekeeper.commands;

import it.unibs.core.StoreRegister;
import it.unibs.ui.Command;

public class ViewStoreRegisterCommand implements Command {

    private final StoreRegister storeRegister;

    public ViewStoreRegisterCommand(StoreRegister storeRegister) {
        this.storeRegister = storeRegister;
    }

    @Override
    public void execute() {
        if (storeRegister.getProducts().isEmpty()) {
            System.out.println("La lista è vuota.");
        } else {
            System.out.println("Nel registro di magazzino sono presenti:");
            storeRegister.getProducts().forEach(System.out::println);
        }
    }
}
