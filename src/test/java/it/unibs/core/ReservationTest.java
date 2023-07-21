package it.unibs.core;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ReservationTest {

    @Test
    void add() {
        Reservation reservation = new Reservation(LocalDate.of(2023, 7, 19), 100);

        Orderable orderable1 = new Dish("Carbonara", new Period(
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2024, 1, 1)));
        Orderable orderable2 = new Dish("Pizza", new Period(
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2024, 1, 1)));
        Orderable orderable3 = new Dish("Sushi", new Period(
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2024, 1, 1)));

        assertFalse(reservation.getOrders().containsKey(orderable1));
        assertFalse(reservation.getOrders().containsKey(orderable2));
        assertFalse(reservation.getOrders().containsKey(orderable3));

        reservation.add(orderable1);
        reservation.add(orderable2);
        reservation.add(orderable3);

        assertEquals(1, reservation.getOrders().get(orderable1).intValue());
        assertEquals(1, reservation.getOrders().get(orderable2).intValue());
        assertEquals(1, reservation.getOrders().get(orderable3).intValue());

        reservation.add(orderable1);

        assertEquals(2, reservation.getOrders().get(orderable1).intValue());
        assertEquals(1, reservation.getOrders().get(orderable2).intValue());
        assertEquals(1, reservation.getOrders().get(orderable3).intValue());

    }

    @Test
    void isExpired() {
        LocalDate target = LocalDate.of(2023, 7, 19);

        Reservation reservation1 = new Reservation(LocalDate.of(2023, 7, 18), 100);
        Reservation reservation2 = new Reservation(LocalDate.of(2023, 7, 19), 100);
        Reservation reservation3 = new Reservation(LocalDate.of(2023, 7, 20), 100);

        assertTrue(reservation1.isExpired(target));
        assertFalse(reservation2.isExpired(target));
        assertFalse(reservation3.isExpired(target));
    }
}