package it.unibs.ui.storekeeper.commands;

import it.unibs.core.Restaurant;
import it.unibs.ui.Command;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateStoreRegisterCommand implements Command {

    private final Restaurant restaurant;

    @Override
    public void onSelection() {

    }
}
