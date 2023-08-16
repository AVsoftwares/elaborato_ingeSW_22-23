package it.unibs.core;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ThematicMenuTest {

    @Test
    void isValidAtDate() {
        ThematicMenu thematicMenu = new ThematicMenu(
                "TestMenu",
                new Period(LocalDate.of(2023, 1, 1), LocalDate.of(2024, 1, 1)),
                new ArrayList<>());

        assertTrue(thematicMenu.isValidAtDate(LocalDate.of(2023, 1, 1)));
        assertTrue(thematicMenu.isValidAtDate(LocalDate.of(2024, 1, 1)));
        assertTrue(thematicMenu.isValidAtDate(LocalDate.of(2023, 7, 19)));

        assertFalse(thematicMenu.isValidAtDate(LocalDate.of(2022, 12, 31)));
        assertFalse(thematicMenu.isValidAtDate(LocalDate.of(2024, 1, 2)));
    }
}