package it.unibs.core;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Rappresenta un periodo di validità, a cui è associata una data di inizio ed una di fine
 */
public class Period {
    /**
     * Formatter di default usato per il parsing e la rappresentazione in stringa
     */
    public static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yy");
    /**
     * Data di inizio periodo, inclusivo
     */
    private final LocalDate startDate;
    /**
     * Data di fine periodo, inclusivo
     */
    private final LocalDate endDate;

    /**
     * @param startDate la data di inizio periodo
     * @param endDate   la data di fine periodo
     * @throws IllegalArgumentException se la data di inizio periodo è successiva alla data di fine periodo
     */
    public Period(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalStateException("Start date must be before end date");
        }
        this.startDate = Objects.requireNonNull(startDate);
        this.endDate = Objects.requireNonNull(endDate);
    }

    /**
     * Controlla se il periodo istanza chiamante è precedente ad una data passata come parametro
     *
     * @param date la data rispetto alla quale viene effettuato il controllo
     * @return true se {@link #startDate startDate} e {@link #endDate endDate} sono precedenti alla data in input
     */
    public boolean isBefore(LocalDate date) {
        return startDate.isBefore(date) && endDate.isBefore(date);
    }

    /**
     * Controlla se il periodo istanza chiamante è successivo ad una data passata come parametro
     *
     * @param date la data rispetto alla quale viene effettuato il controllo
     * @return true se {@link #startDate startDate} e {@link #endDate endDate} sono successive alla data in input
     */
    public boolean isAfter(LocalDate date) {
        return startDate.isAfter(date) && endDate.isAfter(date);
    }

    /**
     * Controlla se la data passata come parametro è inclusa nel periodo istanza chiamante
     *
     * @param date la data rispetto alla quale viene effettuato il controllo
     * @return true se {@link #startDate} è precedente alla data in input e {@link #endDate} è successiva
     * alla data in input
     */
    public boolean includes(LocalDate date) {
        return (startDate.isBefore(date) || startDate.isEqual(date)) && (endDate.isAfter(date) || endDate.isEqual(date));
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
