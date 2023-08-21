package it.unibs.controller.reservationOfficer;

import it.unibs.core.Consumable;
import it.unibs.core.Dish;
import it.unibs.core.Restaurant;
import it.unibs.core.ThematicMenu;
import it.unibs.core.reservation.Reservation;
import it.unibs.core.reservation.ReservationService;
import it.unibs.ui.Command;
import it.unibs.ui.InputManager;
import it.unibs.ui.reservationOfficer.ReservationOfficerView;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

public class ReservationCommand implements Command {
    private final Restaurant restaurant;
    private final ReservationService reservationService;
    private final ReservationOfficerView view;

    public ReservationCommand(ReservationOfficerView view, ReservationService reservationService) {
        this.restaurant = Restaurant.getInstance();
        this.reservationService = reservationService;
        this.view = view;
    }

    public void execute() {
        final LocalDate date = InputManager.readDate("Data della prenotazione: ", InputManager.DEFAULT_DATE_FORMATTER_PATTERN);

        if (!reservationService.isValid(date)) {
            view.printInvalidQuantity();
            return;
        }

        final List<ThematicMenu> availableMenus = restaurant.getAvailableMenus(date);
        final List<Dish> availableDishes = restaurant.getAvailableDishes(date);

        final int seats = InputManager.readInt("Numero di coperti da prenotare: ", 1, Integer.MAX_VALUE);
        if (getAvailableSeatsAtDate(date) - seats < 0) {
            view.printNoAvailableSeats();
            return;
        }

        final Reservation reservation = new Reservation(date, seats);

        final List<? extends Consumable> consumables = Stream.concat(availableMenus.stream(), availableDishes.stream()).toList();

        if (!consumables.isEmpty()) {
            addConsumablesToReservation(reservation, consumables);
        } else {
            view.printNothingAvailable();
            return;
        }

        if (canSustainWorkload()) {
            reservationService.add(reservation);
        } else {
            view.printOverWorkload();
        }
    }

    private void addConsumablesToReservation(Reservation reservation, List<? extends Consumable> consumables) {
        for (int i = 0; i < consumables.size(); i++) {
            System.out.println("\t- " + i + " " + consumables.get(i));
        }
        final int choice = InputManager.readInt("Ordine: ", 0, consumables.size());

        reservation.add(consumables.get(choice));
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
