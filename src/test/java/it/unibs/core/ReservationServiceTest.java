package it.unibs.core;

import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ReservationServiceTest {

    private final Clock clock = Clock.fixed(
            Instant.from(LocalDate.of(2023, 7, 19)
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), ZoneId.systemDefault());

    @Test
    void isDateValid() {
        Clock clock = Clock.fixed(
                Instant.from(LocalDate.of(2023, 7, 19)
                        .atStartOfDay(ZoneId.systemDefault())
                        .toInstant()), ZoneId.systemDefault());

        //ReservationService reservationService = new ReservationService(new ArrayList<>(), clock);

        LocalDate date1 = LocalDate.of(2023, 7, 18);
        LocalDate date2 = LocalDate.of(2023, 7, 19);
        LocalDate date3 = LocalDate.of(2023, 7, 22);
        LocalDate date4 = LocalDate.of(2023, 7, 20);

        /**
        Reservation reservation1 = new Reservation(date1, 5);
        Reservation reservation2 = new Reservation(date2, 4);
        Reservation reservation3 = new Reservation(date3, 8);
        Reservation reservation4 = new Reservation(date4, 7);
        **/

        assertFalse(Reservation.isDateValid(date1)); // "La prenotazione è precedente al giorno odierno");
        assertFalse(Reservation.isDateValid(date2)); // "La prenotazione non è pervenuta con un giorno di anticipo");
        assertFalse(Reservation.isDateValid(date3)); // "La prenotazione non è effettuata per giorni feriali");

        assertFalse(Reservation.isDateValid(date4)); //La prenotazione è pervenuta con un giorno di anticipo e per un giorno feriale");
    }

    @Test
    void shouldBeAllReservationsExpired() {
        ArrayList<Reservation> reservations = new ArrayList<>();

        reservations.add(new Reservation(LocalDate.of(2023, 7, 15), 100));
        reservations.add(new Reservation(LocalDate.of(2023, 7, 16), 100));
        reservations.add(new Reservation(LocalDate.of(2023, 7, 17), 100));
        reservations.add(new Reservation(LocalDate.of(2023, 7, 18), 100));

        ReservationService reservationService = new ReservationService(reservations, clock);

        assertTrue(reservationService.getReservations().isEmpty());
    }

    @Test
    void shouldBeSomeReservationsExpired() {
        ArrayList<Reservation> reservations = new ArrayList<>();

        reservations.add(new Reservation(LocalDate.of(2023, 7, 15), 100));
        reservations.add(new Reservation(LocalDate.of(2023, 7, 17), 100));
        reservations.add(new Reservation(LocalDate.of(2023, 7, 26), 100));
        reservations.add(new Reservation(LocalDate.of(2023, 7, 27), 100));

        ReservationService reservationService = new ReservationService(reservations, clock);

        assertTrue(reservationService.getReservations().isEmpty());
    }
}