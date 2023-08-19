package it.unibs.core;

import it.unibs.core.reservation.ConcreteDateValidationStrategy;
import it.unibs.core.reservation.Reservation;
import it.unibs.core.reservation.ReservationService;
import it.unibs.core.unit.MeasureUnit;
import it.unibs.core.unit.MetricPrefix;
import it.unibs.core.unit.Quantity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShoppingListTest {

    private static final Clock CLOCK = Clock.fixed(
            Instant.from(LocalDate.of(2023, 7, 19)
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), ZoneId.systemDefault());

    @Test
    void shouldSimulateCompleteFlow() {
        ArrayList<Reservation> reservations = new ArrayList<>();
        Reservation myReservation = new Reservation(LocalDate.of(2023, 7, 26), 2);

        reservations.add(new Reservation(LocalDate.of(2023, 7, 15), 100));
        reservations.add(new Reservation(LocalDate.of(2023, 7, 17), 100));
        reservations.add(myReservation);

        ReservationService reservationService = new ReservationService(
                reservations,
                new ConcreteDateValidationStrategy(),
                CLOCK);

        Period period= new Period(
                LocalDate.of(2023, 7, 19),
                LocalDate.of(2023, 8, 10));

        Dish dish = new Dish("Pizza",period);
        Dish dish2 = new Dish("Patatine", period);

        Map<Ingredient, Quantity> ingredientsPizza = Map.of(new Ingredient("farina"),new Quantity(180, MetricPrefix.MILLI, MeasureUnit.GRAMS),
                new Ingredient("pomodoro"),new Quantity(100,MetricPrefix.MILLI,MeasureUnit.GRAMS));
        Recipe recipePizza= new Recipe(ingredientsPizza,1,1);

        Map<Ingredient, Quantity> ingredientsPatatine = Map.of(new Ingredient("sale"),new Quantity(50, MetricPrefix.MILLI, MeasureUnit.GRAMS),
                new Ingredient("patate"),new Quantity(300,MetricPrefix.KILO,MeasureUnit.GRAMS));
        Recipe recipePatatine= new Recipe(ingredientsPatatine,1,1);

        dish.setRecipe(recipePizza);
        dish2.setRecipe(recipePatatine);

        ThematicMenu menu = new ThematicMenu("combo",period, List.of(dish,dish2));

        myReservation.add(dish);
        myReservation.add(menu);

        assertTrue(true);


    }
}
