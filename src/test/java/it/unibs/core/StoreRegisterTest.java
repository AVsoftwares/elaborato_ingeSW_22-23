package it.unibs.core;

import it.unibs.core.unit.MeasureUnit;
import it.unibs.core.unit.MetricPrefix;
import it.unibs.core.unit.Quantity;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StoreRegisterTest {

    @Test
    void shouldBeAllAvailable() {
        ArrayList<Product> products = new ArrayList<>();

        products.add(new Product(
                "Pane",
                LocalDate.of(2023, 1, 19),
                new Quantity(10, MetricPrefix.KILO, MeasureUnit.GRAMS)));
        products.add(new Product(
                "Grissini",
                LocalDate.of(2023, 3, 27),
                new Quantity(200, MetricPrefix.NONE, MeasureUnit.UNITS)));
        products.add(new Product(
                "Pomodori",
                LocalDate.of(2023, 5, 31),
                new Quantity(5, MetricPrefix.KILO, MeasureUnit.GRAMS)));
        products.add(new Product(
                "Patate",
                LocalDate.of(2023, 7, 19),
                new Quantity(30, MetricPrefix.KILO, MeasureUnit.GRAMS)));

        StoreRegister storeRegister = new StoreRegister(products, Clock.systemDefaultZone());

        assertTrue(storeRegister.isAvailable("Pane"));
        assertTrue(storeRegister.isAvailable("Grissini"));
        assertTrue(storeRegister.isAvailable("Pomodori"));
        assertTrue(storeRegister.isAvailable("Patate"));
    }

    @Test
    void shouldNotBeAllAvailable() {
        ArrayList<Product> products = new ArrayList<>();

        products.add(new Product(
                "Pane",
                LocalDate.of(2023, 1, 19),
                new Quantity(10, MetricPrefix.KILO, MeasureUnit.GRAMS)));
        products.add(new Product(
                "Grissini",
                LocalDate.of(2023, 3, 27),
                new Quantity(200, MetricPrefix.NONE, MeasureUnit.UNITS)));
        products.add(new Product(
                "Pomodori",
                LocalDate.of(2023, 5, 31),
                new Quantity(5, MetricPrefix.KILO, MeasureUnit.GRAMS)));
        products.add(new Product(
                "Patate",
                LocalDate.of(2023, 7, 19),
                new Quantity(30, MetricPrefix.KILO, MeasureUnit.GRAMS)));

        StoreRegister storeRegister = new StoreRegister(products, Clock.systemDefaultZone());

        assertTrue(storeRegister.isAvailable("Pane"));
        assertTrue(storeRegister.isAvailable("Grissini"));
        assertTrue(storeRegister.isAvailable("Pomodori"));
        assertTrue(storeRegister.isAvailable("Patate"));

        assertFalse(storeRegister.isAvailable("Basilico"));
    }

    @Test
    void shouldRemoveAllProducts() {
        ArrayList<Product> products = new ArrayList<>();

        products.add(new Product(
                "Pane",
                LocalDate.of(2023, 1, 19),
                new Quantity(10, MetricPrefix.KILO, MeasureUnit.GRAMS)));
        products.add(new Product(
                "Grissini",
                LocalDate.of(2023, 3, 27),
                new Quantity(200, MetricPrefix.NONE, MeasureUnit.UNITS)));
        products.add(new Product(
                "Pomodori",
                LocalDate.of(2023, 5, 31),
                new Quantity(5, MetricPrefix.KILO, MeasureUnit.GRAMS)));

        StoreRegister storeRegister = new StoreRegister(products, Clock.fixed(
                LocalDate.of(2023, 7, 19)
                        .atStartOfDay(ZoneId.systemDefault())
                        .toInstant(),
                ZoneId.systemDefault()));

        assertTrue(storeRegister.isAvailable("Pane"));
        assertTrue(storeRegister.isAvailable("Grissini"));
        assertTrue(storeRegister.isAvailable("Pomodori"));

        storeRegister.removeExpiredProducts();

        assertFalse(storeRegister.isAvailable("Pane"));
        assertFalse(storeRegister.isAvailable("Grissini"));
        assertFalse(storeRegister.isAvailable("Pomodori"));
    }

    @Test
    void shouldRemoveSomeProducts() {
        ArrayList<Product> products = new ArrayList<>();

        products.add(new Product(
                "Pane",
                LocalDate.of(2023, 1, 19),
                new Quantity(10, MetricPrefix.KILO, MeasureUnit.GRAMS)));
        products.add(new Product(
                "Grissini",
                LocalDate.of(2023, 3, 27),
                new Quantity(200, MetricPrefix.NONE, MeasureUnit.UNITS)));
        products.add(new Product(
                "Pomodori",
                LocalDate.of(2023, 5, 31),
                new Quantity(5, MetricPrefix.KILO, MeasureUnit.GRAMS)));
        products.add(new Product(
                "Patate",
                LocalDate.of(2023, 7, 19),
                new Quantity(30, MetricPrefix.KILO, MeasureUnit.GRAMS)));

        StoreRegister storeRegister = new StoreRegister(products, Clock.fixed(
                LocalDate.of(2023, 7, 19)
                        .atStartOfDay(ZoneId.systemDefault())
                        .toInstant(),
                ZoneId.systemDefault()));

        assertTrue(storeRegister.isAvailable("Pane"));
        assertTrue(storeRegister.isAvailable("Grissini"));
        assertTrue(storeRegister.isAvailable("Pomodori"));
        assertTrue(storeRegister.isAvailable("Patate"));

        storeRegister.removeExpiredProducts();

        assertFalse(storeRegister.isAvailable("Pane"));
        assertFalse(storeRegister.isAvailable("Grissini"));
        assertFalse(storeRegister.isAvailable("Pomodori"));
        assertTrue(storeRegister.isAvailable("Patate"));
    }
}