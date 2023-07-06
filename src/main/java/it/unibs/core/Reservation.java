package it.unibs.core;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Objects;

public class Reservation {
    private final HashMap<ThematicMenu, Integer> thematicMenus = new HashMap<>();
    private final HashMap<Dish, Integer> dishes = new HashMap<>();
    private final LocalDate date;
    private final int seats;

    public Reservation(LocalDate date, int seats) {
        if (seats <= 0) {
            throw new IllegalArgumentException("Seats must be greater than 0");
        }
        this.date = Objects.requireNonNull(date);
        this.seats = seats;
    }

    public void add(ThematicMenu menu) {
        if (thematicMenus.containsKey(menu)) {
            thematicMenus.put(menu, thematicMenus.get(menu) + 1);
        } else {
            thematicMenus.put(menu, 1);
        }
    }

    public void add(Dish dish) {
        if (dishes.containsKey(dish)) {
            dishes.put(dish, dishes.get(dish) + 1);
        } else {
            dishes.put(dish, 1);
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

    public HashMap<ThematicMenu, Integer> getThematicMenus() {
        return thematicMenus;
    }

    public HashMap<Dish, Integer> getDishes() {
        return dishes;
    }

    public int getSeats() {
        return seats;
    }

    public float getWorkload() {
        float workload = 0f;

        for (var entry : dishes.entrySet()) {
            workload += entry.getKey().getWorkload() * entry.getValue();
        }

        for (var entry : thematicMenus.entrySet()) {
            workload += entry.getKey().getTotalWorkload() * entry.getValue();
        }

        return workload;
    }
}
