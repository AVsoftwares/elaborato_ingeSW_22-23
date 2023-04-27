package it.unibs.ui.login;

import it.unibs.core.Restaurant;
import it.unibs.ui.Command;
import it.unibs.ui.manager.ManagerMenu;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ManagerLoginCommand implements Command {

    private final Restaurant restaurant;

    @Override
    public void onSelection() {
        ManagerMenu menu = new ManagerMenu(restaurant);

        menu.run();
    }

}
