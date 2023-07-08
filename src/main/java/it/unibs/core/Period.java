package it.unibs.core;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Period {
    private static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yy");
    private final LocalDate startDate;
    private final LocalDate endDate;

    public Period(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalStateException("Start date must be before end date");
        }
        this.startDate = Objects.requireNonNull(startDate);
        this.endDate = Objects.requireNonNull(endDate);
    }

    public boolean isBefore(LocalDate date) {
        return startDate.isBefore(date) && endDate.isBefore(date);
    }

    public boolean isAfter(LocalDate date) {
        return startDate.isAfter(date) && endDate.isAfter(date);
    }

    public boolean includes(LocalDate date) {
        return startDate.isBefore(date) && endDate.isAfter(date);
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String format(DateTimeFormatter formatter) {
        return startDate.format(formatter) + "-" + endDate.format(formatter);
    }

    @Override
    public String toString() {
        return format(DEFAULT_FORMATTER);
    }
}
