package it.unibs.core;

import it.unibs.core.unit.MeasureUnit;
import it.unibs.core.unit.MetricPrefix;
import it.unibs.core.unit.Quantity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
class RestaurantTest {

    @Test
    void drinkConsumptionNotSet(){
        Restaurant r = new Restaurant();
        r.addDrink("acqua");

        assertTrue(r.isAverageDrinkConsumptionNotSet("acqua"));
    }

    @Test
    void drinkConsumptionSet(){
        Restaurant r = new Restaurant();

        r.addDrink("Vino");
        Quantity q = new Quantity(2, MetricPrefix.NONE, MeasureUnit.LITERS);
        r.setAverageDrinkConsumption("Vino", q );

        assertFalse(r.isAverageDrinkConsumptionNotSet("Vino"));
    }
}
