package it.unibs.core;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Objects;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * Una prenotazione effettuata presso il ristorante
 */
public class Reservation {
    /**
     * Coppie ordine-intero dove un ordine è qualcosa che può essere ordinato presso il ristorante,
     * deve implementare {@link Orderable}
     */
    private final HashMap<Orderable, Integer> orders = new HashMap<>();
    /**
     * Data per cui è stata fatta la prenotazione
     */
    private final LocalDate date;
    //private Clock clock;
    /**
     * Numero di posti a sedere prenotati
     */
    private final int seats;

    /**
     * Ritorna un'istanza di Reservation
     * @param date data prenotata
     * @param seats numero di posti a sedere prenotati
     * @throws IllegalArgumentException se i posti a sedere sono minori o uguali a 0
     */
    public Reservation(LocalDate date, int seats) {
        if (seats <= 0) {
            throw new IllegalArgumentException("Seats must be greater than 0");
        }
        this.date = Objects.requireNonNull(date);
        this.seats = seats;
    }

    /**
     * Aggiunge un {@link Orderable} alla Map degli ordini ed aggiorna il relativo numero intero associato
     * @param orderable ordine da aggiungere alla Map
     */
    public void add(Orderable orderable) {
        if (orders.containsKey(orderable)) {
            orders.put(orderable, orders.get(orderable) + 1);
        } else {
            orders.put(orderable, 1);
        }
    }

    /**
     * Controlla se la prenotazione è scaduta.
     * Una prenotazione è scaduta se antecedente alla data passata come parametro.
     * @param target la data rispetto alla quale si controlla se la prenotazione è scaduta
     * @return true se la prenotazione è scaduta, false altrimenti
     */
    public boolean isExpired(LocalDate target) {
        return date.isBefore(target);
    }

    public LocalDate getDate() {
        return date;
    }

    public HashMap<Orderable, Integer> getOrders() {
        return orders;
    }

    public int getSeats() {
        return seats;
    }

    /**
     * getter per il calcolo del carico di lavoro della prenotazione
     * @return il carico di lavoro complessivo della prenotazione
     */
    public float getWorkload() {
        float workload = 0f;

        for (var entry : orders.entrySet()) {
            workload += entry.getKey().getWorkload() * entry.getValue();
        }

        return workload;
    }

    /**
     * Valida la prenotazione.
     * Una prenotazione è valida se la sua data è feriale, antecedente
     * alla data odierna e pervenuta con almeno un giorno feriale di anticipo.
     * @return true se la data è valida, false altrimenti
     */
    public static boolean isDateValid(LocalDate date) {

        final var today = LocalDate.now(Clock.systemDefaultZone());

        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return (dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY && DAYS.between(today, date) >= 1);
    }
}
