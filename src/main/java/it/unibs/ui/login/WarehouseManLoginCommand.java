package it.unibs.ui.login;

import it.unibs.core.Restaurant;
import it.unibs.ui.Command;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WarehouseManLoginCommand implements Command {

    private final Restaurant restaurant;

    @Override
    public void onSelection() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onSelection'");
    }

}
