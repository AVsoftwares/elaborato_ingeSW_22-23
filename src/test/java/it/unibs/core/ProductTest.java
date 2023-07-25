package it.unibs.core;

import it.unibs.core.unit.MeasureUnit;
import it.unibs.core.unit.MetricPrefix;
import it.unibs.core.unit.Quantity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

class ProductTest {

    @Test
    void shouldBeAllAvailable() {
        Product product1 = new Product("Mozzarella", LocalDate.of(2023, 7, 19), new Quantity(0.1f, MetricPrefix.KILO, MeasureUnit.GRAMS));
        Product product2 = new Product("Farina", LocalDate.of(2023, 7, 20), new Quantity(0.0001f, MetricPrefix.NONE, MeasureUnit.LITERS));
        Product product3 = new Product("Zucchero", LocalDate.of(2023, 7, 21), new Quantity(1, MetricPrefix.NONE, MeasureUnit.UNITS));

        assertTrue(product1.isAvailable());
        assertTrue(product2.isAvailable());
        assertTrue(product3.isAvailable());
    }

    @Test
    void shouldBeNoneAvailable() {
        Product product1 = new Product("Mozzarella", LocalDate.of(2023, 7, 19), new Quantity(0, MetricPrefix.KILO, MeasureUnit.GRAMS));
        Product product2 = new Product("Farina", LocalDate.of(2023, 7, 20), new Quantity(0.000f, MetricPrefix.NONE, MeasureUnit.LITERS));
        Product product3 = new Product("Zucchero", LocalDate.of(2023, 7, 21), null);

        assertFalse(product1.isAvailable());
        assertFalse(product2.isAvailable());
        assertFalse(product3.isAvailable());
    }

    @Test
    void shouldBeAllValid() {
        Product product1 = new Product("Mozzarella", LocalDate.of(2023, 7, 19), null);
        Product product2 = new Product("Farina", LocalDate.of(2023, 7, 20), null);
        Product product3 = new Product("Zucchero", LocalDate.of(2023, 7, 21), null);

        assertTrue(product1.isValidAtDate(LocalDate.of(2023, 7, 19)));
        assertTrue(product2.isValidAtDate(LocalDate.of(2023, 7, 19)));
        assertTrue(product3.isValidAtDate(LocalDate.of(2023, 7, 19)));
    }

    @Test
    void shouldBeNoneValid() {
        Product product1 = new Product("Mozzarella", LocalDate.of(2023, 7, 16), null);
        Product product2 = new Product("Farina", LocalDate.of(2023, 7, 17), null);
        Product product3 = new Product("Zucchero", LocalDate.of(2023, 7, 18), null);

        assertFalse(product1.isValidAtDate(LocalDate.of(2023, 7, 19)));
        assertFalse(product2.isValidAtDate(LocalDate.of(2023, 7, 19)));
        assertFalse(product3.isValidAtDate(LocalDate.of(2023, 7, 19)));
    }
}