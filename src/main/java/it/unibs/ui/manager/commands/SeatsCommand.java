package it.unibs.ui.manager.commands;

import it.unibs.core.Restaurant;
import it.unibs.ui.Command;
import it.unibs.ui.InputManager;
import it.unibs.ui.manager.ManagerView;

public class SeatsCommand implements Command {

    public static final String RESTAURANT_SEATS = "Inserisci il numero di posti a sedere del ristorante: ";
    private final Restaurant restaurant = Restaurant.getInstance();
    private final ManagerView view;

    public SeatsCommand(ManagerView view) {
        this.view = view;
    }

    @Override
    public void execute() {
        final var seats = restaurant.getSeats();

        if (seats == 0) {
            view.printValueNotInitialized();
            restaurant.setSeats(InputManager.readInt(RESTAURANT_SEATS));
        } else {
            view.printCurrentValue(seats);
        }
    }
}
