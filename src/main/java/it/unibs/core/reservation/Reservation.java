package it.unibs.core.reservation;

import it.unibs.core.Consumable;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Objects;

/**
 * Una prenotazione effettuata presso il ristorante
 */
public class Reservation {
    /**
     * Coppie ordine-intero dove un ordine è qualcosa che può essere ordinato presso il ristorante,
     * deve implementare {@link Consumable}
     */
    private final HashMap<Consumable, Integer> orders = new HashMap<>();
    /**
     * Data per cui è stata fatta la prenotazione
     */
    private final LocalDate date;
    /**
     * Numero di posti a sedere prenotati
     */
    private final int seats;

    /**
     * Ritorna un'istanza di Reservation
     *
     * @param date  data prenotata
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
     * Aggiunge un {@link Consumable} alla Map degli ordini ed aggiorna il relativo numero intero associato
     *
     * @param consumable ordine da aggiungere alla Map
     */
    public void add(Consumable consumable) {
        if (orders.containsKey(consumable)) {
            orders.put(consumable, orders.get(consumable) + 1);
        } else {
            orders.put(consumable, 1);
        }
    }

    /**
     * Controlla se la prenotazione è scaduta.
     * Una prenotazione è scaduta se antecedente alla data passata come parametro.
     *
     * @param target la data rispetto alla quale si controlla se la prenotazione è scaduta
     * @return true se la prenotazione è scaduta, false altrimenti
     */
    public boolean isExpired(LocalDate target) {
        return date.isBefore(target);
    }

    public LocalDate getDate() {
        return date;
    }

    public HashMap<Consumable, Integer> getOrders() {
        return orders;
    }

    public int getSeats() {
        return seats;
    }

    /**
     * getter per il calcolo del carico di lavoro della prenotazione
     *
     * @return il carico di lavoro complessivo della prenotazione
     */
    public float getWorkload() {
        float workload = 0f;

        for (var entry : orders.entrySet()) {
            workload += entry.getKey().getWorkload() * entry.getValue();
        }

        return workload;
    }
}
