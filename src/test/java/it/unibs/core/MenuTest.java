package it.unibs.core;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MenuTest {

    @Test
    void shouldNotAddEqualDishes() {
        Menu menu = new Menu("TestMenu", new ArrayList<>());

        Dish dish = new Dish("TestDish1", new Period(
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 12, 31)));

        assertTrue(menu.add(dish));
        assertFalse(menu.add(dish));
    }
}