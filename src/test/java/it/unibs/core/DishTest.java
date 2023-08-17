package it.unibs.core;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DishTest {

    @Test
    void shouldNoneBeExpired() {
        Dish dish = new Dish("TestDish", new Period(
                LocalDate.of(2023, 7, 19),
                LocalDate.of(2023, 8, 10)
        ));

        LocalDate date1 = LocalDate.of(2023, 7, 19);
        LocalDate date2 = LocalDate.of(2023, 7, 24);
        LocalDate date3 = LocalDate.of(2023, 8, 10);

        assertFalse(dish.isExpiredAtDate(date1));
        assertFalse(dish.isExpiredAtDate(date2));
        assertFalse(dish.isExpiredAtDate(date3));
    }

    @Test
    void shouldAllBeExpired() {
        Dish dish = new Dish("TestDish", new Period(
                LocalDate.of(2023, 7, 19),
                LocalDate.of(2023, 8, 10)
        ));

        LocalDate date1 = LocalDate.of(2023, 7, 18);
        LocalDate date2 = LocalDate.of(2023, 7, 1);
        LocalDate date3 = LocalDate.of(2023, 8, 11);

        assertTrue(dish.isExpiredAtDate(date1));
        assertTrue(dish.isExpiredAtDate(date2));
        assertTrue(dish.isExpiredAtDate(date3));
    }
}