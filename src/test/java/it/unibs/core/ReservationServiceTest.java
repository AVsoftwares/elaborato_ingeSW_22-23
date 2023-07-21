package it.unibs.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.*;

class ReservationServiceTest {

    @Test
    @DisplayName("Test validazione della data della prenotazione")
    void isDateValid() {
        Clock clock = Clock.fixed(
                Instant.from(LocalDate.of(2023, 7, 19)
                        .atStartOfDay(ZoneId.systemDefault())
                        .toInstant()), ZoneId.systemDefault());

        ReservationService reservationService = new ReservationService(clock);

        LocalDate date1 = LocalDate.of(2023, 7, 18);
        LocalDate date2 = LocalDate.of(2023, 7, 19);
        LocalDate date3 = LocalDate.of(2023, 7, 22);
        LocalDate date4 = LocalDate.of(2023, 7, 20);

        assertFalse(reservationService.isDateValid(date1), "La prenotazione è precedente al giorno odierno");
        assertFalse(reservationService.isDateValid(date2), "La prenotazione non è pervenuta con un giorno di anticipo");
        assertFalse(reservationService.isDateValid(date3), "La prenotazione non è effettuata per giorni feriali");

        assertTrue(reservationService.isDateValid(date4), "La prenotazione è pervenuta con un giorno di anticipo e per un giorno feriale");
    }
}