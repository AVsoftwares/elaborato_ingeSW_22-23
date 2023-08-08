package it.unibs.core.unit;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class QuantityTest {

    @Test
    void shouldReturnEmptyOptional() {
        assertEquals(Optional.empty(), Quantity.fromString("letters"));
        assertEquals(Optional.empty(), Quantity.fromString("1kg"));
        assertEquals(Optional.empty(), Quantity.fromString("kg1"));
        assertEquals(Optional.empty(), Quantity.fromString("kg 1"));
        assertEquals(Optional.empty(), Quantity.fromString("1 kgletters"));
        assertEquals(Optional.empty(), Quantity.fromString("1 kg 1"));
        assertEquals(Optional.empty(), Quantity.fromString("a"));
        assertEquals(Optional.empty(), Quantity.fromString(" "));
        assertEquals(Optional.empty(), Quantity.fromString("     a    "));
        assertEquals(Optional.empty(), Quantity.fromString("     1kg    "));
    }

    @Test
    void shouldReturnOptionalQuantity() {
        assertEquals(
                Optional.of(new Quantity(12345, MetricPrefix.NONE, MeasureUnit.UNITS)),
                Quantity.fromString("12345"));

        assertEquals(
                Optional.of(new Quantity(0, MetricPrefix.NONE, MeasureUnit.UNITS)),
                Quantity.fromString("0"));

        assertEquals(
                Optional.of(new Quantity(0, MetricPrefix.NONE, MeasureUnit.UNITS)),
                Quantity.fromString("   0   "));

        assertEquals(
                Optional.of(new Quantity(1, MetricPrefix.KILO, MeasureUnit.GRAMS)),
                Quantity.fromString("1 kg"));

        assertEquals(
                Optional.of(new Quantity(1, MetricPrefix.NONE, MeasureUnit.LITERS)),
                Quantity.fromString("1 l"));

        assertEquals(
                Optional.of(new Quantity(1, MetricPrefix.NONE, MeasureUnit.LITERS)),
                Quantity.fromString("   1 l   "));
    }
}