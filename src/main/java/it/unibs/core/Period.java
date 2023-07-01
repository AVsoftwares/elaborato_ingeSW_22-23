package it.unibs.core;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Period {
    private static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yy");
    private final LocalDate startDate;
    private final LocalDate endDate;

    public Period(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException();
        }
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean isBefore(LocalDate date) {
        return date.isBefore(startDate) && date.isBefore(endDate);
    }

    public boolean isAfter(LocalDate date) {
        return date.isAfter(startDate) && date.isAfter(endDate);
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
