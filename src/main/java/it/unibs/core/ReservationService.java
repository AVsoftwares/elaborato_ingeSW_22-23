package it.unibs.core;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

public class ReservationService {
    private final List<Reservation> reservations = new ArrayList<>();

    public List<Reservation> getReservations() {
        return getReservations(LocalDate.now());
    }

    public List<Reservation> getReservations(LocalDate date) {
        removeExpiredReservations();
        return reservations.stream()
                .filter(reservation -> reservation.getDate().isEqual(date))
                .toList();
    }

    public boolean add(Reservation reservation) {
        if (isDateValid(reservation.getDate())) {
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
    public boolean isDateValid(LocalDate date) {
        final var today = LocalDate.now();

        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return (dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY && DAYS.between(today, date) >= 1);
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
