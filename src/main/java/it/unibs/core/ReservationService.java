package it.unibs.core;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * Gestisce le prenotazioni
 */
public class ReservationService {
    /**
     * Lista di prenotazioni effettuate
     */
    private final List<Reservation> reservations = new ArrayList<>();
    private final Clock clock;

    public ReservationService() {
        this(Clock.systemDefaultZone());
    }

    public ReservationService(Clock clock) {
        this.clock = clock;
    }

    /**
     * getter delle prenotazioni odierne
     * @return le prenotazioni odierne
     * @see #getReservations(LocalDate)
     */
    public List<Reservation> getReservations() {
        return getReservations(LocalDate.now(clock));
    }

    /**
     * getter delle prenotazioni ad una data specificata come parametro
     * @param date la data per cui si vuole la lista delle prenotazioni
     * @return la lista delle prenotazioni nella data specificata
     */
    public List<Reservation> getReservations(LocalDate date) {
        removeExpiredReservations();
        return reservations.stream()
                .filter(reservation -> reservation.getDate().isEqual(date))
                .toList();
    }

    /**
     * Aggiunge prenotazioni alla lista delle prenotazioni, solamente se la data della prenotazione è valida
     * rispetto alle specifiche del ristorante relativamente alla prenotazione
     * @param reservation la prenotazione da aggiungere
     * @return true se la prenotazione è stata aggiunta, false altrimenti
     */
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
     * @return true se la data è valida, false altrimenti
     */
    public boolean isDateValid(LocalDate date) {
        final var today = LocalDate.now(clock);

        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return (dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY && DAYS.between(today, date) >= 1);
    }

    /**
     * Rimuove le prenotazioni scadute
     */
    private void removeExpiredReservations() {
        reservations.removeIf(Reservation::isExpired);
    }

    /**
     * Calcola il carico di lavoro complessivo delle prenotazioni nella data passata come parametro
     * @param date la data per cui si vuole calcolare il carico di lavoro
     * @return il carico di lavoro complessivo
     */
    public float getWorkload(LocalDate date) {
        return (float) reservations.stream()
                .filter(reservation -> reservation.getDate().isEqual(date))
                .mapToDouble(Reservation::getWorkload)
                .sum();
    }
}
