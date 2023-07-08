package it.unibs.core;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Objects;

public class Reservation {
    private final HashMap<Orderable, Integer> orders = new HashMap<>();
    private final LocalDate date;
    private final int seats;

    public Reservation(LocalDate date, int seats) {
        if (seats <= 0) {
            throw new IllegalArgumentException("Seats must be greater than 0");
        }
        this.date = Objects.requireNonNull(date);
        this.seats = seats;
    }

    public void add(Orderable orderable) {
        if (orders.containsKey(orderable)) {
            orders.put(orderable, orders.get(orderable) + 1);
        } else {
            orders.put(orderable, 1);
        }
    }

    /**
     * Controlla se la prenotazione è scaduta.
     * Una prenotazione è scaduta se antecedente alla data odierna.
     *
     * @return true se la prenotazione è scaduta, false altrimenti
     */
    public boolean isExpired() {
        return date.isBefore(LocalDate.now());
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

    public float getWorkload() {
        float workload = 0f;

        for (var entry : orders.entrySet()) {
            workload += entry.getKey().getWorkload() * entry.getValue();
        }

        return workload;
    }
}
