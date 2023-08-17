package it.unibs.core.reservation;

import it.unibs.core.Consumable;
import it.unibs.core.Dish;
import it.unibs.core.Period;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ReservationTest {

    @Test
    void shouldAddProductAndIncrementValue() {
        Reservation reservation = new Reservation(LocalDate.of(2023, 7, 19), 100);

        Consumable consumable1 = new Dish("Carbonara", new Period(
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2024, 1, 1)));
        Consumable consumable2 = new Dish("Pizza", new Period(
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2024, 1, 1)));
        Consumable consumable3 = new Dish("Sushi", new Period(
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2024, 1, 1)));

        assertFalse(reservation.getOrders().containsKey(consumable1));
        assertFalse(reservation.getOrders().containsKey(consumable2));
        assertFalse(reservation.getOrders().containsKey(consumable3));

        reservation.add(consumable1);
        reservation.add(consumable2);
        reservation.add(consumable3);

        assertEquals(1, reservation.getOrders().get(consumable1).intValue());
        assertEquals(1, reservation.getOrders().get(consumable2).intValue());
        assertEquals(1, reservation.getOrders().get(consumable3).intValue());

        reservation.add(consumable1);

        assertEquals(2, reservation.getOrders().get(consumable1).intValue());
        assertEquals(1, reservation.getOrders().get(consumable2).intValue());
        assertEquals(1, reservation.getOrders().get(consumable3).intValue());
    }

    @Test
    void shouldBeAllExpired() {
        LocalDate target = LocalDate.of(2023, 7, 19);

        Reservation reservation3 = new Reservation(LocalDate.of(2023, 7, 16), 100);
        Reservation reservation2 = new Reservation(LocalDate.of(2023, 7, 17), 100);
        Reservation reservation1 = new Reservation(LocalDate.of(2023, 7, 18), 100);

        assertTrue(reservation1.isExpired(target));
        assertTrue(reservation2.isExpired(target));
        assertTrue(reservation3.isExpired(target));
    }

    @Test
    void shouldBeNoneExpired() {
        LocalDate target = LocalDate.of(2023, 7, 19);

        Reservation reservation3 = new Reservation(LocalDate.of(2023, 7, 25), 100);
        Reservation reservation2 = new Reservation(LocalDate.of(2023, 7, 26), 100);
        Reservation reservation1 = new Reservation(LocalDate.of(2023, 7, 27), 100);

        assertFalse(reservation1.isExpired(target));
        assertFalse(reservation2.isExpired(target));
        assertFalse(reservation3.isExpired(target));
    }

    @Test
    void shouldNotBeExpiredToday() {
        LocalDate target = LocalDate.of(2023, 7, 19);

        Reservation reservation = new Reservation(LocalDate.of(2023, 7, 19), 100);

        assertFalse(reservation.isExpired(target));
    }
}