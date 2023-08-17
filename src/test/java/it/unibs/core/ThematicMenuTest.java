package it.unibs.core;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ThematicMenuTest {

    @Test
    void shouldNotBeExpired() {
        ThematicMenu thematicMenu = new ThematicMenu(
                "TestMenu",
                new Period(LocalDate.of(2023, 1, 1), LocalDate.of(2024, 1, 1)),
                new ArrayList<>());

        assertFalse(thematicMenu.isExpiredAtDate(LocalDate.of(2023, 1, 1)));
        assertFalse(thematicMenu.isExpiredAtDate(LocalDate.of(2024, 1, 1)));
        assertFalse(thematicMenu.isExpiredAtDate(LocalDate.of(2023, 7, 19)));
    }

    @Test
    void shouldBeExpired() {
        ThematicMenu thematicMenu = new ThematicMenu(
                "TestMenu",
                new Period(LocalDate.of(2023, 1, 1), LocalDate.of(2024, 1, 1)),
                new ArrayList<>());

        assertTrue(thematicMenu.isExpiredAtDate(LocalDate.of(2022, 12, 31)));
        assertTrue(thematicMenu.isExpiredAtDate(LocalDate.of(2024, 1, 2)));
    }
}