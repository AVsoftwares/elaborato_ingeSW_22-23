package it.unibs.core;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PeriodTest {

    @Test
    void shouldBeBefore() {
        Period period = new Period(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 7, 19));

        assertTrue(period.isBefore(LocalDate.of(2023, 7, 20)));
    }

    @Test
    void shouldNotBeBefore() {
        Period period = new Period(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 7, 19));

        assertFalse(period.isBefore(LocalDate.of(2023, 7, 19)));
    }

    @Test
    void shouldBeAfter() {
        Period period = new Period(LocalDate.of(2023, 1, 2), LocalDate.of(2023, 7, 19));

        assertTrue(period.isAfter(LocalDate.of(2023, 1, 1)));
    }

    @Test
    void shouldNotBeAfter() {
        Period period = new Period(LocalDate.of(2023, 1, 2), LocalDate.of(2023, 7, 19));

        assertFalse(period.isAfter(LocalDate.of(2023, 1, 2)));
    }

    @Test
    void shouldAllBeIncluded() {
        Period period1 = new Period(LocalDate.of(2023, 7, 19), LocalDate.of(2023, 7, 19));
        Period period2 = new Period(LocalDate.of(2023, 7, 1), LocalDate.of(2023, 7, 19));

        assertTrue(period1.includes(LocalDate.of(2023, 7, 19)));
        assertTrue(period2.includes(LocalDate.of(2023, 7, 1)));
        assertTrue(period2.includes(LocalDate.of(2023, 7, 19)));
        assertTrue(period2.includes(LocalDate.of(2023, 7, 17)));
    }
}