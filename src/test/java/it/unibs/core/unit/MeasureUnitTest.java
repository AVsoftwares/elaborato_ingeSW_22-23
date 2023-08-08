package it.unibs.core.unit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class MeasureUnitTest {
    @Test
    void allValidUnits() {
        assertEquals(MeasureUnit.GRAMS, MeasureUnit.fromString("g"));
        assertEquals(MeasureUnit.LITERS, MeasureUnit.fromString("l"));
        assertEquals(MeasureUnit.UNITS, MeasureUnit.fromString("u"));
    }

    @Test
    void notValidUnits(){
        assertNotEquals(MeasureUnit.LITERS, MeasureUnit.fromString("g"));
        assertNotEquals(MeasureUnit.UNITS, MeasureUnit.fromString("l"));
        assertNotEquals(MeasureUnit.GRAMS, MeasureUnit.fromString("u"));

        assertNotEquals(MeasureUnit.GRAMS, "k");
        assertNotEquals(MeasureUnit.UNITS, "j");
        assertNotEquals(MeasureUnit.LITERS, "asda");
    }
}
