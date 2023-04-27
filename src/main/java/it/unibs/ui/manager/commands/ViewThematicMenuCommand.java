package it.unibs.ui.manager.commands;

import it.unibs.core.Restaurant;
import it.unibs.ui.Command;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ViewThematicMenuCommand implements Command {

    private final Restaurant restaurant;

    @Override
    public void onSelection() {
        System.out.println("Attualmente sono presenti i seguenti menu tematici:");

        restaurant.getMenus().forEach(m -> {
            System.out.println("- " + m);
        });
    }

}
