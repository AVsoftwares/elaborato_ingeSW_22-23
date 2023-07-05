package it.unibs.core;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class ReservationService {
    private final List<Reservation> reservations = new ArrayList<>();

    public List<Reservation> getReservations() {
        removeExpiredReservations();
        return reservations;
    }

    public List<Reservation> getReservations(LocalDate date) {
        removeExpiredReservations();
        return reservations.stream()
                .filter(reservation -> reservation.getDate().isEqual(date))
                .toList();
    }

    public boolean add(Reservation reservation) {
        if (isDateReservable(reservation.getDate())) {
            return reservations.add(reservation);
        }
        return false;
    }

    /**
     * Valida la prenotazione.
     * Una prenotazione è valida se la sua data è feriale, antecedente
     * alla data odierna e pervenuta con almeno un giorno feriale di anticipo.
     *
     * @return true se la data è valida, false altrimenti
     */
    public boolean isDateReservable(LocalDate date) {
        // TODO test
        final var today = LocalDate.now();

        if (date.isBefore(today)) {
            return false;
        }

        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return (dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY && ChronoUnit.DAYS.between(today, date) >= 1);
    }

    private void removeExpiredReservations() {
        reservations.removeIf(Reservation::isExpired);
    }

    public float getWorkload(LocalDate date) {
        return (float) reservations.stream()
                .filter(reservation -> reservation.getDate().isEqual(date))
                .mapToDouble(Reservation::getWorkload)
                .sum();
    }
}
