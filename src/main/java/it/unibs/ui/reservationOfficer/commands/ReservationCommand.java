package it.unibs.ui.reservationOfficer.commands;

import it.unibs.core.Consumable;
import it.unibs.core.Dish;
import it.unibs.core.Restaurant;
import it.unibs.core.ThematicMenu;
import it.unibs.core.reservation.Reservation;
import it.unibs.core.reservation.ReservationService;
import it.unibs.ui.Command;
import it.unibs.ui.InputManager;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

public class ReservationCommand implements Command {
    private final Restaurant restaurant;
    private final ReservationService reservationService;

    public ReservationCommand(ReservationService reservationService) {
        this.restaurant = Restaurant.getInstance();
        this.reservationService = reservationService;
    }

    public void execute() {
        final LocalDate date = InputManager.readDate("Data della prenotazione: ", InputManager.DEFAULT_DATE_FORMATTER_PATTERN);

        if (!reservationService.isValid(date)) {
            System.out.println("""
                    La data inserita non è valida.
                    Il ristorante lavora unicamente nei giorni feriali.
                    La prenotazione deve essere effettuata con almeno un giorno feriale di anticipo.""");
            return;
        }

        final List<ThematicMenu> availableMenus = restaurant.getAvailableMenus(date);
        final List<Dish> availableDishes = restaurant.getAvailableDishes(date);

        final int seats = InputManager.readInt("Numero di coperti da prenotare: ", 1, Integer.MAX_VALUE);
        if (getAvailableSeatsAtDate(date) - seats < 0) {
            System.out.println("Non ci sono sufficienti posti disponibili per la data selezionata.");
            return;
        }

        final Reservation reservation = new Reservation(date, seats);

        final List<? extends Consumable> orderables = Stream.concat(availableMenus.stream(), availableDishes.stream()).toList();

        if (!orderables.isEmpty()) {
            for (int i = 0; i < orderables.size(); i++) {
                System.out.println("\t- " + i + " " + orderables.get(i));
            }
            final int choice = InputManager.readInt("Ordine: ", 0, orderables.size());

            reservation.add(orderables.get(choice));
        } else {
            System.out.println("Non sono disponibili né piatti né menu per il giorno selezionato, impossibile continuare.");
            return;
        }

        if (canSustainWorkload()) {
            reservationService.add(reservation);
        } else {
            System.out.println("Il ristorante non è in grado di gestire il carico di lavoro.");
        }
    }

    private int getAvailableSeatsAtDate(LocalDate date) {
        return restaurant.getSeats() - reservationService.getReservations().stream()
                .filter(b -> b.getDate().isEqual(date))
                .mapToInt(Reservation::getSeats)
                .sum();
    }

    private boolean canSustainWorkload() {
        return restaurant.getSustainableWorkload() - reservationService.getWorkload(LocalDate.now()) >= 0;
    }
}
