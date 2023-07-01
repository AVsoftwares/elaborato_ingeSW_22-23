package it.unibs.ui.reservationOfficer.commands;

import it.unibs.core.*;
import it.unibs.ui.Command;
import it.unibs.ui.InputManager;
import it.unibs.ui.Menu;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
public class ReservationCommand implements Command {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yy");
    private final Restaurant restaurant;

    public void onSelection() {
        final ReservationManager reservationManager = restaurant.getReservationManager();

        final LocalDate date = InputManager.readDate("Data della prenotazione (dd/MM/yy): ",
                DATE_FORMATTER);

        if (reservationManager.isDateReservable(date)) {
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
            System.out.println("Non sono disponibili né piatti né menu per il giorno selezionato, impossibile continuare.");
            return;
        }


        final int seats = InputManager.readInt("Numero di coperti da prenotare: ", 1, Integer.MAX_VALUE);

        if (availableSeats - seats < 0) {
            System.out.println("Non ci sono sufficienti posti disponibili.");
            return;
        }

        final Reservation reservation = new Reservation(date, seats);

        final Menu menu = new Menu();
        for (int i = 0; i < seats; i++) {
            menu.setTitle("Prenotazione per il coperto " + i);

            menu.addEntry("Menu tematico", () -> {
                if (availableMenus.isEmpty()) {
                    System.out.println("Non sono disponibili menu per la data selezionata.");
                    return;
                }

                for (int j = 0; j < availableMenus.size(); j++) {
                    System.out.println("- " + j + availableMenus.get(j));
                }

                reservation.addThematicMenu(
                        availableMenus.get(
                                InputManager.readInt("Menu tematico: ", 0, availableMenus.size())));
            });
            menu.addEntry("Piatti singoli", () -> {
                if (availableDishes.isEmpty()) {
                    System.out.println("Non sono disponibili piatti per la data selezionata.");
                    return;
                }

                for (int j = 0; j < availableDishes.size(); j++) {
                    System.out.println("- " + j + availableDishes.get(j));
                }

                reservation.addDish(
                        availableDishes.get(
                                InputManager.readInt("Piatto: ", 0, availableDishes.size())));
            });

            menu.run();
        }

        if (!restaurant.canSustainWorkload(reservation.getWorkload())) {
            System.out.println("Il ristorante non è in grado di gestire il carico di lavoro.");
            return;
        }

        if (!restaurant.addReservation(reservation)) {
            System.out.println("La prenotazione non è valida, impossibile continuare.");
        }
    }
}
