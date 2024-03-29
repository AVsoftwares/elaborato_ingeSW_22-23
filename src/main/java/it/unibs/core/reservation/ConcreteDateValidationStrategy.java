package it.unibs.core.reservation;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class ConcreteDateValidationStrategy implements DateValidationStrategy {

    private final Clock clock;

    public ConcreteDateValidationStrategy() {
        this(Clock.systemDefaultZone());
    }

    public ConcreteDateValidationStrategy(Clock clock) {
        this.clock = clock;
    }

    /**
     * Valida la prenotazione.
     * Una prenotazione è valida se la sua data è feriale, antecedente
     * alla data odierna e pervenuta con almeno un giorno feriale di anticipo.
     *
     * @return true se la data è valida, false altrimenti
     */
    @Override
    public boolean isValid(LocalDate date) {
        final var today = LocalDate.now(clock);

        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return (dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY && DAYS.between(today, date) >= 1);
    }
}
