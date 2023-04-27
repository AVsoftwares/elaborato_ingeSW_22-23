package it.unibs.ui.manager.commands;

import java.time.format.DateTimeFormatter;

import it.unibs.core.Restaurant;
import it.unibs.core.ThematicMenu;
import it.unibs.ui.Command;
import it.unibs.ui.InputManager;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateThematicMenuCommand implements Command {

    private final Restaurant restaurant;

    @Override
    public void onSelection() {
        var menuName = InputManager.readString("Inserisci il nome del menu tematico: ");

        ThematicMenu menu = new ThematicMenu(menuName);
        menu.setStartDate(InputManager.readDate("Inserisci la data di inizio validità (dd/MM/yy): ",
                DateTimeFormatter.ofPattern("dd/MM/yy")));
        menu.setExpireDate(InputManager.readDate("Inserisci la data di fine validità (dd/MM/yy): ",
                DateTimeFormatter.ofPattern("dd/MM/yy")));

        var exit = false;
        while (!exit) {
            System.out.println("I piatti disponibili sono: ");

            final var dishes = restaurant.getDishes();

            var menuWorkload = menu.getTotalWorkload();
            for (int i = 0; i < dishes.size(); i++) {
                var dish = dishes.get(i);

                if (menuWorkload + dish.getWorkload() < (float) 4 / 3 * restaurant.getIndividualWorkload()) {
                    System.out.println((i + 1) + "\t" + dish.getName());
                }
            }

            System.out.println("Sono mostrati solo piatti il cui carico di lavoro è ammissibile per il menu tematico!");
            
            menu.addDish(
                    dishes.get(InputManager.readInt("Seleziona dalla lista quale piatto vuoi inserire nel menu: ", 1,
                            dishes.size())));

            exit = !InputManager.readYesOrNo("Vuoi inserire un altro piatto? (y)es/(n)o: ");
        }

        restaurant.addMenu(menu);
        System.out.printf("Il menu tematico \"%s\" è stato memorizzato%n", menu.getName());
    }

}
