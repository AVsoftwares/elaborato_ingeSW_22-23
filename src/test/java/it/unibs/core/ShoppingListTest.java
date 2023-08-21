package it.unibs.core;

import it.unibs.core.reservation.ConcreteDateValidationStrategy;
import it.unibs.core.reservation.Reservation;
import it.unibs.core.reservation.ReservationService;
import it.unibs.core.unit.MeasureUnit;
import it.unibs.core.unit.MetricPrefix;
import it.unibs.core.unit.Quantity;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShoppingListTest {

    private static final Clock CLOCK = Clock.fixed(
            Instant.from(LocalDate.of(2023, 7, 19)
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), ZoneId.systemDefault());
    private static final Clock CLOCK_VALID_DATE = Clock.fixed(
            Instant.from(LocalDate.of(2023, 7, 3)
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), ZoneId.systemDefault());

    @Test
    void shouldSimulateCompleteFlow() {
        ReservationService reservationService = new ReservationService(
                new ArrayList<>(),
                new ConcreteDateValidationStrategy(CLOCK_VALID_DATE),
                CLOCK);
        StoreRegister storeRegister = new StoreRegister(new ArrayList<>(), CLOCK);
        ShoppingList shoppingList = new ShoppingList(storeRegister, reservationService);
        storeRegister.attach(shoppingList);
        storeRegister.attach(Restaurant.getInstance());

        Period period = new Period(
                LocalDate.of(2023, 7, 19),
                LocalDate.of(2023, 8, 10));

        Dish pizza = new Dish("Pizza", period);
        Dish fries = new Dish("Patatine", period);

        Recipe pizzaRecipe = new Recipe(new HashMap<>(), 1, 1);
        pizzaRecipe.add(new Ingredient("farina"), new Quantity(180, MetricPrefix.NONE, MeasureUnit.GRAMS));
        pizzaRecipe.add(new Ingredient("pomodoro"), new Quantity(100, MetricPrefix.NONE, MeasureUnit.GRAMS));
        pizza.setRecipe(pizzaRecipe);

        Recipe friesRecipe = new Recipe(new HashMap<>(), 1, 1);
        friesRecipe.add(new Ingredient("sale"), new Quantity(50, MetricPrefix.NONE, MeasureUnit.GRAMS));
        friesRecipe.add(new Ingredient("patate"), new Quantity(3, MetricPrefix.KILO, MeasureUnit.GRAMS));
        fries.setRecipe(friesRecipe);

        ThematicMenu menu = new ThematicMenu("TestMenu", period, List.of(pizza, fries));

        Reservation reservation1 = new Reservation(LocalDate.of(2023, 7, 19), 100);
        reservation1.add(menu);
        Reservation reservation2 = new Reservation(LocalDate.of(2023, 7, 20), 2);
        reservation2.add(pizza);
        Reservation reservation3 = new Reservation(LocalDate.of(2023, 7, 21), 4);
        reservation3.add(fries);

        reservationService.add(reservation1);
        reservationService.add(reservation2);
        reservationService.add(reservation3);

        storeRegister.add(new Product(
                "farina",
                LocalDate.of(2023, 9, 1),
                new Quantity(180, MetricPrefix.NONE, MeasureUnit.GRAMS)));

        assertFalse(shoppingList.contains("farina"));
        assertTrue(shoppingList.contains("pomodoro"));
        assertTrue(shoppingList.contains("sale"));
        assertTrue(shoppingList.contains("patate"));
    }
}
