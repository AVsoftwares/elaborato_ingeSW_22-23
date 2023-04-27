package it.unibs.ui.manager.commands;

import it.unibs.core.Restaurant;
import it.unibs.ui.Command;
import it.unibs.ui.InputManager;
import it.unibs.ui.Menu;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HandleExtraAmountCommand implements Command {

    private final Restaurant restaurant;
    @Override
    public void onSelection() {
        Menu menu = new Menu("Gestione consumo pro-capite di alimenti extra");

        var mapAvgExtra = restaurant.getAvgExtraAmount();

        menu.addEntry("Visualizza consumo alimenti extra", () -> {

            if (mapAvgExtra.isEmpty()) {
                System.out.println("La lista è vuota.");
            } else {
                System.out.println("Gli alimenti extra attualmente disponibili sono:");
                mapAvgExtra.keySet().forEach(System.out::println);
            }
        });
        menu.addEntry("Aggiungi nuovo", () -> {
            var name = InputManager.readString("Inserisci il nome dell'alimento extra: ");

            if (mapAvgExtra.containsKey(name)) {
                System.out.println("L'alimento extra è già presente nell'elenco.");
            } else {
                var avgAmount = InputManager.readInt("Inserisci il consumo pro-capite: ");
                mapAvgExtra.put(name, avgAmount);
            }
        });

        menu.run();
    }
}
