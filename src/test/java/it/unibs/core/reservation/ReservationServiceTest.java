package it.unibs.core.reservation;

import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ReservationServiceTest {
    private static final Clock CLOCK = Clock.fixed(
            Instant.from(LocalDate.of(2023, 7, 19)
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), ZoneId.systemDefault());

    @Test
    void shouldBeAllReservationsExpired() {
        ArrayList<Reservation> reservations = new ArrayList<>();

        reservations.add(new Reservation(LocalDate.of(2023, 7, 15), 100));
        reservations.add(new Reservation(LocalDate.of(2023, 7, 16), 100));
        reservations.add(new Reservation(LocalDate.of(2023, 7, 17), 100));
        reservations.add(new Reservation(LocalDate.of(2023, 7, 18), 100));

        ReservationService reservationService = new ReservationService(
                reservations,
                new ConcreteDateValidationStrategy(),
                CLOCK);

        assertTrue(reservationService.getReservations().isEmpty());
    }

    @Test
    void shouldBeSomeReservationsExpired() {
        ArrayList<Reservation> reservations = new ArrayList<>();

        reservations.add(new Reservation(LocalDate.of(2023, 7, 15), 100));
        reservations.add(new Reservation(LocalDate.of(2023, 7, 17), 100));
        reservations.add(new Reservation(LocalDate.of(2023, 7, 26), 100));
        reservations.add(new Reservation(LocalDate.of(2023, 7, 27), 100));

        ReservationService reservationService = new ReservationService(
                reservations,
                new ConcreteDateValidationStrategy(),
                CLOCK);

        assertTrue(reservationService.getReservations().isEmpty());
    }
}