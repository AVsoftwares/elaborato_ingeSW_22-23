package it.unibs.ui.reservationOfficer.commands;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import it.unibs.core.Reservation;
import it.unibs.core.Dish;
import it.unibs.core.Restaurant;
import it.unibs.core.ThematicMenu;
import it.unibs.ui.Command;
import it.unibs.ui.InputManager;
import it.unibs.ui.Menu;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReservationCommand implements Command {

    private final Restaurant restaurant;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yy");

    public void onSelection() {
        final LocalDate date = InputManager.readDate("Data della prenotazione (dd/MM/yy): ",
                DATE_FORMATTER);

        if (!Reservation.isValid(date)) {
            System.out.println("La data inserita non è valida.");
            return;
        }

        final int availableSeats = restaurant.getAvailableSeatsAtDate(date);
        final List<ThematicMenu> availableMenus = restaurant.getAvailableMenusAtDate(date);
        final List<Dish> availableDishes = restaurant.getAvailableDishesAtDate(date);

        if (availableSeats == 0) {
            System.out.println("Non ci sono posti disponibili.");
            return;
        }

        if (availableDishes.isEmpty() && availableMenus.isEmpty()) {
            System.out.println(
                    "Non sono disponibili né piatti né menu per il giorno selezionato, impossibile continuare.");
            return;
        }

        final int seats = InputManager.readInt("Inserisci il numero di coperti che vuoi prenotare: ", 1,
                Integer.MAX_VALUE);

        if (availableSeats - seats < 0) {
            System.out.println("Non ci sono sufficienti posti disponibili.");
            return;
        }

        Reservation reservation = new Reservation(date, seats);

        for (int i = 0; i < seats; i++) {
            Menu menu = new Menu("Prenotazione per il coperto " + i);

            if (!availableMenus.isEmpty()) {
                menu.addEntry("Menu tematico", () -> {
                    System.out.println("Per la data selezionata sono disponibili i seguenti menu tematici:");
    
                    for (int j = 0; j < availableMenus.size(); j++) {
                        System.out.println("- " + j + availableMenus.get(j));
                    }
    
                    final var index = InputManager.readInt("Inserisci il menu tematico scelto: ", 0, availableMenus.size());
                    reservation.addThematicMenu(availableMenus.get(index));
                });
            }

            if (!availableDishes.isEmpty()) {
                menu.addEntry("Piatti singoli", () -> {

                    System.out.println("Per la data selezionata sono disponibili i seguenti piatti:");

                    for (int j = 0; j < availableDishes.size(); j++) {
                        System.out.println("- " + j + availableDishes.get(j));
                    }

                    final var index = InputManager.readInt("Inserisci il piatto scelto: ", 0, availableDishes.size());
                    reservation.addDish(availableDishes.get(index));
                });
            }

            menu.run();
        }

        if (restaurant.getAvailableWorkload() - reservation.getWorkload() < 0) {
            System.out.println("Il ristorante non è in grado di gestire il carico di lavoro.");
            return;
        }

        restaurant.addBooking(reservation);
    }
}
