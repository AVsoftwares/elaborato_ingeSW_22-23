package it.unibs.ui.manager.commands;

import it.unibs.core.Restaurant;
import it.unibs.ui.Command;
import it.unibs.ui.InputManager;
import it.unibs.ui.Menu;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class ExtraAmountCommand implements Command {

    private final Restaurant restaurant;

    @Override
    public void execute() {
        final Menu menu = new Menu("Gestione consumo pro-capite di alimenti extra");

        final Map<String, Float> mapAvgExtra = restaurant.getAvgExtraAmount();

        menu.addEntry("Visualizza consumo pro-capite di alimenti extra", () -> {
            final var extraWithConsumption = mapAvgExtra.entrySet().stream()
                    .filter(e -> e.getValue() != null)
                    .toList();

            if (extraWithConsumption.isEmpty()) {
                System.out.println("Non è presente nessun alimento extra con consumo pro-capite associato.");
            } else {
                extraWithConsumption.forEach(System.out::println);
            }
        });
        menu.addEntry("Inizializza il consumo pro-capite di alimenti extra", () -> {
            final String name = InputManager.readString("Nome dell'alimento extra: ").toLowerCase();

            if (mapAvgExtra.containsKey(name)) {
                mapAvgExtra.put(name, InputManager.readFloat("Consumo pro-capite: "));
            } else {
                System.out.println("Non è presente nessun alimento extra \"" + name + "\".");
            }
        });

        menu.run();
    }
}
