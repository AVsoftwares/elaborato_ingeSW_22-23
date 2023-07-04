package it.unibs.core;

import it.unibs.core.unit.Quantity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingList {
    private final Map<Product, Quantity> products = new HashMap<>();

    private final StoreRegister storeRegister;
    private final ReservationService reservationService;

    public ShoppingList(StoreRegister storeRegister, ReservationService reservationService) {
        this.storeRegister = storeRegister;
        this.reservationService = reservationService;
        computeShoppingList();
    }

    private void computeShoppingList() {
        for (Reservation r : reservationService.getReservations()) {
            final Map<Dish, Integer> dishBookings = r.getDishBookings();
            final Map<ThematicMenu, Integer> thematicMenuBookings = r.getThematicMenuBookings();

            for (Map.Entry<Dish, Integer> dishes : dishBookings.entrySet()) {
                final Map<Ingredient, Quantity> ingredients = dishes.getKey().getRecipe().getIngredients();

                ingredients.forEach((ingredient, quantity) -> quantity.multiply(dishes.getValue()));

                // TODO devo metterli solo se non presenti, se presenti incrementare quantità
                products.putAll(ingredients);
            }

            for (Map.Entry<ThematicMenu, Integer> menus : thematicMenuBookings.entrySet()) {
                final List<Dish> dishes = menus.getKey().getDishes();

                for (Dish dish : dishes) {
                    final Map<Ingredient, Quantity> ingredients = dish.getRecipe().getIngredients();
                    ingredients.forEach((key, value) -> value.multiply(menus.getValue()));

                    // TODO devo metterli solo se non presenti, se presenti incrementare quantità
                    products.putAll(ingredients);
                }
            }
        }
        // TODO differenza con prodotti in StoreRegister, per sapere quanto devo acquistare
    }

    public Map<Product, Quantity> getProducts() {
        return products;
    }
}
