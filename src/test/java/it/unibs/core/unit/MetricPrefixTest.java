package it.unibs.core.unit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class MetricPrefixTest {

    @Test
    void allValidPrefix() {
        assertEquals(MetricPrefix.MILLI, MetricPrefix.fromString("m"));
        assertEquals(MetricPrefix.CENTI, MetricPrefix.fromString("c"));
        assertEquals(MetricPrefix.DECI, MetricPrefix.fromString("d"));
        assertEquals(MetricPrefix.DECA, MetricPrefix.fromString("da"));
        assertEquals(MetricPrefix.HECTO, MetricPrefix.fromString("h"));
        assertEquals(MetricPrefix.KILO, MetricPrefix.fromString("k"));
    }

    @Test
    void notValidPrefix() {
        assertNotEquals(MetricPrefix.MILLI, MetricPrefix.fromString("k"));
        assertNotEquals(MetricPrefix.CENTI, MetricPrefix.fromString("m"));
        assertNotEquals(MetricPrefix.DECI, MetricPrefix.fromString("c"));
        assertNotEquals(MetricPrefix.DECA, MetricPrefix.fromString("d"));
        assertNotEquals(MetricPrefix.HECTO, MetricPrefix.fromString("da"));
        assertNotEquals(MetricPrefix.KILO, MetricPrefix.fromString("h"));

        assertNotEquals(MetricPrefix.MILLI, "g2");
        assertNotEquals(MetricPrefix.CENTI, "tre2");
        assertNotEquals(MetricPrefix.DECI, "dec");
        assertNotEquals(MetricPrefix.DECA, "deca");
        assertNotEquals(MetricPrefix.HECTO, "etto");
        assertNotEquals(MetricPrefix.KILO, "kilogrammo");

    }


}