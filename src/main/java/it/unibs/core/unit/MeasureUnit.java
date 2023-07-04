package it.unibs.core.unit;

public enum MeasureUnit {
    GRAMS("g"),
    LITERS("l"),
    UNITS("u");

    private final String symbol;

    MeasureUnit(String symbol) {
        this.symbol = symbol;
    }

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
