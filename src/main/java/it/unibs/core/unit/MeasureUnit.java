package it.unibs.core.unit;

import java.util.Objects;

/**
 * Enum per la rappresentazione delle unità di misura: grammi, litri e unità
 */
public enum MeasureUnit {
    /**
     * Unità di misura rappresentante i grammi
     */
    GRAMS("g"),
    /**
     * Unità di misura rappresentante i litri
     */
    LITERS("l"),
    /**
     * Unità di misura rappresentante le unità
     */
    UNITS("u");

    private final String symbol;

    MeasureUnit(String symbol) {
        this.symbol = Objects.requireNonNull(symbol);
    }

    /**
     * Effettua il parsing da una stringa per ottenere una valida istanza di MeasureUnit
     *
     * @param value rappresenta il simbolo dell'unità di misura (g, l, u o null)
     * @return MeasureUnit
     * @throws IllegalArgumentException se non esiste unità con simbolo uguale all'argomento
     */
    public static MeasureUnit fromString(String value) {
        for (MeasureUnit unit : MeasureUnit.values()) {
            if (unit.symbol.equalsIgnoreCase(value == null ? "u" : value)) {
                return unit;
            }
        }
        throw new IllegalArgumentException("No unit with symbol " + value);
    }

    public String getSymbol() {
        return symbol;
    }
}
