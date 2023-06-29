package it.unibs.ui.manager.commands;

import it.unibs.core.Dish;
import it.unibs.core.Restaurant;
import it.unibs.core.ThematicMenu;
import it.unibs.ui.Command;
import it.unibs.ui.InputManager;
import it.unibs.ui.Menu;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
public class ThematicMenuCommand implements Command {

    private final Restaurant restaurant;

    /**
     * @see Command
     */
    @Override
    public void onSelection() {
        final Menu menu = new Menu("Gestione menu tematici");

        menu.addEntry("Visualizza menu tematici", () -> {
            final List<ThematicMenu> thematicMenus = restaurant.getThematicMenus();

            if (thematicMenus.isEmpty()) {
                System.out.println("La lista è vuota.");
            } else {
                System.out.println("I menu tematici memorizzati sono:");
                thematicMenus.forEach(System.out::println);
            }
        });
        menu.addEntry("Aggiungi nuovo", () -> {
            final String name = InputManager.readString("Inserisci il nome del menu tematico: ");
            final LocalDate startDate = InputManager.readDate("Inserisci la data di inizio validità (dd/MM/yy): ",
                    DateTimeFormatter.ofPattern("dd/MM/yy"));
            final LocalDate expireDate = InputManager.readDate("Inserisci la data di fine validità (dd/MM/yy): ",
                    DateTimeFormatter.ofPattern("dd/MM/yy"));

            if (startDate.isAfter(expireDate)) {
                System.out.println("La data di scadenza inserita è precedente alla data di inizio validità, impossibile continuare.");
                return;
            }

            final ThematicMenu thematicMenu = new ThematicMenu(name, startDate, expireDate);
            final List<Dish> dishes = restaurant.getDishes();

            do {
                System.out.println("I piatti disponibili sono:");
                for (int i = 0; i < dishes.size(); i++) {
                    System.out.println(i + 1 + "\t" + dishes.get(i));
                }

                final Dish dish = dishes.get(
                        InputManager.readInt(
                                "Seleziona dalla lista quale piatto vuoi inserire nel menu: ", 1,
                                dishes.size()) - 1);

                final int menuWorkload = thematicMenu.getTotalWorkload();
                if (menuWorkload + dish.getWorkload() >= (float) 4 / 3 * restaurant.getIndividualWorkload()) {
                    System.out.println("Il piatto scelto ha un carico di lavoro incompatibile con il menu.");
                    continue;
                }

                if (!thematicMenu.addDish(dish)) {
                    System.out.println("Il piatto scelto è già presente nel menu.");
                }

            } while (InputManager.readYesOrNo("Vuoi inserire un altro piatto? (y)es/(n)o: "));

            restaurant.addMenu(thematicMenu);
        });

        menu.run();
    }
}
