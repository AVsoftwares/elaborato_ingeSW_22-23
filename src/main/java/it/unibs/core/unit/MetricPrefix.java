package it.unibs.core.unit;

public enum MetricPrefix implements Unit {
    MILLI("m", -3),
    CENTI("c", -2),
    DECI("d", -1),
    DECA("d", 1),
    HECTO("h", 2),
    KILO("k", 3);

    private final String symbol;
    private final int exponent;

    MetricPrefix(String symbol, int exponent) {
        this.symbol = symbol;
        this.exponent = exponent;
    }

    public static MetricPrefix fromString(String prefix) {
        for (MetricPrefix metricPrefix : MetricPrefix.values()) {
            if (metricPrefix.symbol.equalsIgnoreCase(prefix)) {
                return metricPrefix;
            }
        }
        throw new IllegalArgumentException("No prefix with symbol " + prefix + " found.");
    }

    public String getSymbol() {
        return symbol;
    }

    public int getExponent() {
        return exponent;
    }
}
