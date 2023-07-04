package it.unibs.ui.manager.commands;

import it.unibs.core.Restaurant;
import it.unibs.ui.Command;
import it.unibs.ui.InputManager;
import it.unibs.ui.Menu;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExtraCommand implements Command {

    private final Restaurant restaurant;

    @Override
    public void execute() {
        Menu menu = new Menu("Gestione generi alimentari extra");
        var mapAvgExtra = restaurant.getAvgExtraAmount();

        menu.addEntry("Visualizza generi alimentari extra", () -> {
            if (mapAvgExtra.isEmpty()) {
                System.out.println("La lista è vuota.");
            } else {
                System.out.println("I generi alimentari extra attualmente disponibili sono:");
                mapAvgExtra.keySet().forEach(System.out::println);
            }
        });
        menu.addEntry("Aggiungi genere alimentare", () -> {
            var name = InputManager.readString("Nome: ");

            if (mapAvgExtra.containsKey(name)) {
                System.out.println("Il genere alimentare è già presente nell'elenco.");
            } else {
                mapAvgExtra.put(name, null);
            }
        });

        menu.run();
    }
}
