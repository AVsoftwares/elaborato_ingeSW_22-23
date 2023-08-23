package it.unibs.core.reservation;

import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConcreteDateValidationStrategyTest {

    public static final Clock CLOCK = Clock.fixed(
            Instant.from(LocalDate.of(2023, 7, 19)
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), ZoneId.systemDefault());

    @Test
    void shouldAllDatesBeNotValid() {
        ReservationService reservationService = new ReservationService(
                new ArrayList<>(),
                new ConcreteDateValidationStrategy(CLOCK),
                CLOCK);

        LocalDate date1 = LocalDate.of(2023, 7, 18);
        LocalDate date2 = LocalDate.of(2023, 7, 19);
        LocalDate date3 = LocalDate.of(2023, 7, 22);
        LocalDate date4 = LocalDate.of(2023, 7, 23);

        assertFalse(reservationService.isValid(date1));
        assertFalse(reservationService.isValid(date2));
        assertFalse(reservationService.isValid(date3));
        assertFalse(reservationService.isValid(date4));
    }

    @Test
    void shouldAllDatesBeValid() {
        ReservationService reservationService = new ReservationService(
                new ArrayList<>(),
                new ConcreteDateValidationStrategy(CLOCK),
                CLOCK);

        LocalDate date1 = LocalDate.of(2023, 7, 21);
        LocalDate date2 = LocalDate.of(2023, 7, 24);
        LocalDate date3 = LocalDate.of(2023, 7, 25);
        LocalDate date4 = LocalDate.of(2023, 7, 26);

        assertTrue(reservationService.isValid(date1));
        assertTrue(reservationService.isValid(date2));
        assertTrue(reservationService.isValid(date3));
        assertTrue(reservationService.isValid(date4));
    }
}