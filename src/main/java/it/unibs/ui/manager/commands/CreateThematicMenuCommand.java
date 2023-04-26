package it.unibs.ui.manager.commands;

import it.unibs.core.Restaurant;
import it.unibs.core.ThematicMenu;
import it.unibs.ui.Command;
import it.unibs.ui.InputManager;

public class CreateThematicMenuCommand implements Command {

    private final Restaurant restaurant;

    public CreateThematicMenuCommand(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void onSelection() {
        var menuName = InputManager.readString("Inserisci il nome del menu tematico: ");

        ThematicMenu menu = new ThematicMenu(menuName);

        var exit = false;
        while (!exit) {
            exit = !InputManager.readYesOrNo("Vuoi inserire un altro piatto? (y)es/(n)o: ");
        }

        restaurant.addMenus(menu);
        System.out.printf("Il menu tematico \"%s\" è stato memorizzato%n", menu.getName());
    }

}
