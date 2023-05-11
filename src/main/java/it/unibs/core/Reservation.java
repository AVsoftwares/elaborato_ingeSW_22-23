package it.unibs.core;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

public class Reservation {
    private final LocalDate date;
    private final int seats;

    private final HashMap<ThematicMenu, Integer> thematicMenuBookings;
    private final HashMap<Dish, Integer> dishBookings;

    public Reservation(LocalDate date, int seats) {
        this.thematicMenuBookings = new HashMap<>();
        this.dishBookings = new HashMap<>();
        this.date = date;
        this.seats = seats;
    }

    public void addThematicMenu(ThematicMenu menu) {
        if (thematicMenuBookings.containsKey(menu)) {
            thematicMenuBookings.put(menu, thematicMenuBookings.get(menu) + 1);
        } else {
            thematicMenuBookings.put(menu, 1);
        }
    }

    public void addDish(Dish dish) {
        if (dishBookings.containsKey(dish)) {
            dishBookings.put(dish, dishBookings.get(dish) + 1);
        } else {
            dishBookings.put(dish, 1);
        }
    }

    /**
     * Valida la prenotazione.
     * Una prenotazione è valida se la sua data è feriale, antecedente
     * alla data odierna e pervenuta con almeno un giorno feriale di anticipo.
     *
     * @return true se la data è valida, false altrimenti
     */
    public static boolean isValid(LocalDate date) {
        // TODO test
        final var today = LocalDate.now();

        if (date.isBefore(today)) {
            return false;
        }

        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return (dayOfWeek == DayOfWeek.SATURDAY && ChronoUnit.DAYS.between(today, date) >= 6)
                || (dayOfWeek == DayOfWeek.SUNDAY && ChronoUnit.DAYS.between(today, date) >= 1);
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

    public HashMap<ThematicMenu, Integer> getThematicMenuBookings() {
        return thematicMenuBookings;
    }

    public HashMap<Dish, Integer> getDishBookings() {
        return dishBookings;
    }

    public int getSeats() {
        return seats;
    }

    public float getWorkload() {
        float workload = 0f;

        for (var entry : dishBookings.entrySet()) {
            workload += entry.getKey().getWorkload() * entry.getValue();
        }

        for (var entry : thematicMenuBookings.entrySet()) {
            workload += entry.getKey().getTotalWorkload() * entry.getValue();
        }

        return workload;
    }
}
