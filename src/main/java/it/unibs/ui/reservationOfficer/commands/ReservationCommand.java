package it.unibs.ui.reservationOfficer.commands;

import it.unibs.core.*;
import it.unibs.ui.Command;
import it.unibs.ui.InputManager;
import it.unibs.ui.Menu;

import java.time.LocalDate;
import java.util.List;

public class ReservationCommand implements Command {
    private final Restaurant restaurant;
    private final ReservationService reservationService;

    public ReservationCommand(Restaurant restaurant, ReservationService reservationService) {
        this.restaurant = restaurant;
        this.reservationService = reservationService;
    }

    public void execute() {
        final LocalDate date = InputManager.readDate("Data della prenotazione: ", InputManager.DEFAULT_DATE_FORMATTER_PATTERN);

        if (reservationService.isDateReservable(date)) {
            System.out.println("""
                    La data inserita non è valida.
                    Il ristorante lavora unicamente nei giorni feriali.
                    La prenotazione deve essere effettuata con almeno un giorno feriale di anticipo.""");
            return;
        }


        final List<ThematicMenu> availableMenus = restaurant.getAvailableMenus(date);
        final List<Dish> availableDishes = restaurant.getAvailableDishes(date);


        if (availableDishes.isEmpty() && availableMenus.isEmpty()) {
            System.out.println("Non sono disponibili né piatti né menu per il giorno selezionato, impossibile continuare.");
            return;
        }

        final int seats = InputManager.readInt("Numero di coperti da prenotare: ", 1, Integer.MAX_VALUE);
        if (getAvailableSeatsAtDate(date) - seats < 0) {
            System.out.println("Non ci sono sufficienti posti disponibili per la data selezionata.");
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
                    System.out.println("\t- " + j + availableMenus.get(j));
                }
                final int choice = InputManager.readInt("Menu tematico: ", 0, availableMenus.size());

                reservation.addThematicMenu(availableMenus.get(choice));
            });
            menu.addEntry("Piatti singoli", () -> {
                if (availableDishes.isEmpty()) {
                    System.out.println("Non sono disponibili piatti per la data selezionata.");
                    return;
                }

                for (int j = 0; j < availableDishes.size(); j++) {
                    System.out.println("- " + j + availableDishes.get(j));
                }
                final int choice = InputManager.readInt("Piatto: ", 0, availableDishes.size());

                reservation.addDish(availableDishes.get(choice));
            });

            menu.run();
        }

        if (canSustainWorkload()) {
            if (!reservationService.addReservation(reservation)) {
                System.out.println("La prenotazione non è valida, impossibile continuare.");
            }
        } else {
            System.out.println("Il ristorante non è in grado di gestire il carico di lavoro.");
        }
    }

    private int getAvailableSeatsAtDate(LocalDate date) {
        return restaurant.getSeats() - reservationService.getReservations().stream().filter(b -> b.getDate().isEqual(date)).mapToInt(Reservation::getSeats).sum();
    }

    private boolean canSustainWorkload() {
        return restaurant.getSustainableWorkload() - reservationService.getWorkload(LocalDate.now()) >= 0;
    }
}
