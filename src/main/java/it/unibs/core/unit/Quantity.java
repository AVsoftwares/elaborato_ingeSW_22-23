package it.unibs.core.unit;

import java.util.Optional;

public class Quantity {
    private final MeasureUnit unit;
    private float amount;
    private MetricPrefix prefix;

    private Quantity(float amount, MetricPrefix prefix, MeasureUnit unit) {
        this.amount = amount;
        this.prefix = prefix;
        this.unit = unit;
    }

    public static Optional<Quantity> fromString(String value) {
        final String[] split = value.split(" ");

        final float amount = Float.parseFloat(split[0]);

        if (split.length == 1) {
            return Optional.of(new Quantity(amount, MetricPrefix.NONE, MeasureUnit.UNITS));
        }

        if (split.length == 2) {
            if (split[1].length() == 1) {
                final MeasureUnit unit = MeasureUnit.fromString(String.valueOf(split[1].charAt(0)));
                return Optional.of(new Quantity(amount, MetricPrefix.NONE, unit));
            } else if (split[1].length() == 2) {
                final MetricPrefix prefix = MetricPrefix.fromString(String.valueOf(split[1].charAt(0)));
                final MeasureUnit unit = MeasureUnit.fromString(String.valueOf(split[1].charAt(1)));
                return Optional.of(new Quantity(amount, prefix, unit));
            }
        }

        return Optional.empty();
    }

    public Quantity add(float value) {
        amount += value;
        return this;
    }

    public Quantity add(Quantity value) {
        value.convertTo(this);
        return add(value.getAmount());
    }

    public Quantity subtract(float value) {
        amount -= value;
        return this;
    }

    public Quantity subtract(Quantity value) {
        value.convertTo(this);
        return subtract(value.getAmount());
    }

    public Quantity multiply(float value) {
        amount *= value;
        return this;
    }

    public Quantity multiply(Quantity value) {
        value.convertTo(this);
        return multiply(value.getAmount());
    }

    public float getAmount() {
        return amount;
    }

    public void convertTo(Quantity quantity) throws IllegalArgumentException {
        if (!unit.equals(quantity.unit)) {
            throw new IllegalArgumentException("Argument must have the same unit of caller class");
        }

        if (prefix.equals(quantity.prefix)) {
            return;
        }

        convertTo(quantity.prefix);
    }

    public void convertTo(MetricPrefix prefix) {
        amount *= 10 * prefix.getExponent();
        this.prefix = prefix;
    }

    @Override
    public String toString() {
        return amount + " " + prefix.getSymbol() + unit.getSymbol();
    }
}
