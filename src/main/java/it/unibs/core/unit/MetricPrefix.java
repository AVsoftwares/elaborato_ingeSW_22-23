package it.unibs.core.unit;

import java.util.Objects;

/**
 * Enum per la rappresentazione dei prefissi metrici, da milli- a kilo-
 */
public enum MetricPrefix {
    MILLI("m", -3),
    CENTI("c", -2),
    DECI("d", -1),
    NONE("", 0),
    DECA("da", 1),
    HECTO("h", 2),
    KILO("k", 3);

    private final String symbol;
    private final int exponent;

    MetricPrefix(String symbol, int exponent) {
        this.symbol = Objects.requireNonNull(symbol);
        this.exponent = exponent;
    }

    public static MetricPrefix fromString(String value) {
        for (MetricPrefix prefix : MetricPrefix.values()) {
            if (prefix.symbol.equalsIgnoreCase(value == null ? "" : value)) {
                return prefix;
            }
        }
        throw new IllegalArgumentException("No prefix with symbol " + value);
    }

    public String getSymbol() {
        return symbol;
    }

    public int getExponent() {
        return exponent;
    }
}
