package it.unibs.core.unit;

public class Quantity {
    private final Unit unit;
    private Number amount;

    public Quantity(Unit unit, Number amount) {
        this.unit = unit;
        this.amount = amount;
    }

    public Unit getUnit() {
        return unit;
    }

    public Number getAmount() {
        return amount;
    }

    public void setAmount(Number amount) {
        this.amount = amount;
    }
}
