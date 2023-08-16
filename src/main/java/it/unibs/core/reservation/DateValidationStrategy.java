package it.unibs.core.reservation;

import java.time.LocalDate;

public interface DateValidationStrategy {
    boolean isValid(LocalDate date);
}
