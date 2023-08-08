package it.unibs.core;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DishTest {

    @Test
    void shouldBeAllValid() {
        Dish dish = new Dish("TestDish", new Period(
                LocalDate.of(2023, 7, 19),
                LocalDate.of(2023, 8, 10)
        ));

        LocalDate date1 = LocalDate.of(2023, 7, 19);
        LocalDate date2 = LocalDate.of(2023, 7, 24);
        LocalDate date3 = LocalDate.of(2023, 8, 10);

        assertTrue(dish.isValidAtDate(date1));
        assertTrue(dish.isValidAtDate(date2));
        assertTrue(dish.isValidAtDate(date3));
    }

    @Test
    void shouldBeNoneValid() {
        Dish dish = new Dish("TestDish", new Period(
                LocalDate.of(2023, 7, 19),
                LocalDate.of(2023, 8, 10)
        ));

        LocalDate date1 = LocalDate.of(2023, 7, 18);
        LocalDate date2 = LocalDate.of(2023, 7, 1);
        LocalDate date3 = LocalDate.of(2023, 8, 11);

        assertFalse(dish.isValidAtDate(date1));
        assertFalse(dish.isValidAtDate(date2));
        assertFalse(dish.isValidAtDate(date3));
    }
}