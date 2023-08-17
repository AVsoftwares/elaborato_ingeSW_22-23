package it.unibs.ui.manager.commands;

import it.unibs.core.Restaurant;
import it.unibs.core.unit.Quantity;
import it.unibs.ui.Command;
import it.unibs.ui.InputManager;
import it.unibs.ui.Menu;

import java.util.Map;

public class ExtraCommand implements Command {

    public static final String MSG_EXTRA_FOOD_MANAGEMENT = "Gestione generi alimentari extra";
    public static final String VIEW_EXTRA_FOOD = "Visualizza generi alimentari extra";
    public static final String MSG_EMPTY_LIST = "La lista è vuota.";
    public static final String ACTUALLY_AVAILABLE = "I generi alimentari extra attualmente disponibili sono:";
    public static final String ADD_EXTRA_FOOD = "Aggiungi genere alimentare";
    public static final String ALREADY_PRESENT = "Il genere alimentare è già presente nell'elenco.";
    private final Restaurant restaurant = Restaurant.getInstance();

    @Override
    public void execute() {
        Menu menu = new Menu(MSG_EXTRA_FOOD_MANAGEMENT);
        final Map<String, Quantity> averageExtraConsumption = restaurant.getImmutableAverageExtraConsumption();

        menu.addEntry(VIEW_EXTRA_FOOD, () -> {
            if (averageExtraConsumption.isEmpty()) {
                System.out.println(MSG_EMPTY_LIST);
            } else {
                System.out.println(ACTUALLY_AVAILABLE);
                averageExtraConsumption.keySet().forEach(System.out::println);
            }
        });
        menu.addEntry(ADD_EXTRA_FOOD, () -> {
            var name = InputManager.readString("Nome: ").toLowerCase();

            if (averageExtraConsumption.containsKey(name)) {
                System.out.println(ALREADY_PRESENT);
            } else {
                restaurant.addExtra(name);
            }
        });

        menu.run();
    }
}
