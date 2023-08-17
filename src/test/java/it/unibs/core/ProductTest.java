package it.unibs.core;

import it.unibs.core.unit.MeasureUnit;
import it.unibs.core.unit.MetricPrefix;
import it.unibs.core.unit.Quantity;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    void shouldNoneBeExpired() {
        LocalDate date1 = LocalDate.of(2023, 7, 19);
        LocalDate date2 = LocalDate.of(2023, 7, 20);
        LocalDate date3 = LocalDate.of(2023, 7, 21);

        Product product1 = new Product("Mozzarella", date1, null);
        Product product2 = new Product("Farina", date2, null);
        Product product3 = new Product("Zucchero", date3, null);

        assertFalse(product1.isExpiredAtDate(date1));
        assertFalse(product2.isExpiredAtDate(date2));
        assertFalse(product3.isExpiredAtDate(date3));
    }

    @Test
    void shouldAllBeExpired() {
        Product product1 = new Product("Mozzarella", LocalDate.of(2023, 7, 16), null);
        Product product2 = new Product("Farina", LocalDate.of(2023, 7, 17), null);
        Product product3 = new Product("Zucchero", LocalDate.of(2023, 7, 18), null);

        LocalDate date1 = LocalDate.of(2023, 7, 17);
        LocalDate date2 = LocalDate.of(2023, 7, 18);
        LocalDate date3 = LocalDate.of(2023, 7, 19);

        assertTrue(product1.isExpiredAtDate(date1));
        assertTrue(product2.isExpiredAtDate(date2));
        assertTrue(product3.isExpiredAtDate(date3));
    }
}