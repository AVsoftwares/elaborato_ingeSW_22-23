package it.unibs.core;

import it.unibs.core.unit.Quantity;

import java.util.HashMap;
import java.util.Map;

public class ShoppingList {
    private final Map<String, Quantity> products = new HashMap<>();
    private final StoreRegister storeRegister;
    private final ReservationService reservationService;

    public ShoppingList(StoreRegister storeRegister, ReservationService reservationService) {
        this.storeRegister = storeRegister;
        this.reservationService = reservationService;
    }

    private void computeShoppingList() {
        for (Reservation reservation : reservationService.getReservations()) {
            final Map<Dish, Integer> dishBookings = reservation.getDishBookings();
            final Map<ThematicMenu, Integer> thematicMenuBookings = reservation.getThematicMenuBookings();

            dishBookings.forEach((dish, bookingsCount) -> {
                final Map<Ingredient, Quantity> ingredients = dish.getRecipe().getIngredients();

                ingredients.forEach((ingredient, quantity) -> {
                    final String ingredientName = ingredient.getName();
                    final Quantity totalQuantity = quantity.multiply(bookingsCount);

                    if (products.containsKey(ingredientName)) {
                        products.put(ingredientName, totalQuantity.add(products.get(ingredientName)));
                    } else {
                        products.put(ingredientName, totalQuantity);
                    }
                });
            });

            thematicMenuBookings.forEach((menu, bookingsCount) -> {
                for (Dish dish : menu.getDishes()) {
                    final Map<Ingredient, Quantity> ingredients = dish.getRecipe().getIngredients();

                    ingredients.forEach((ingredient, quantity) -> {
                        final String ingredientName = ingredient.getName();
                        final Quantity totalQuantity = quantity.multiply(bookingsCount);

                        if (products.containsKey(ingredientName)) {
                            products.put(ingredientName, totalQuantity.add(products.get(ingredientName)));
                        } else {
                            products.put(ingredientName, totalQuantity);
                        }
                    });
                }
            });
        }

        for (Product product : storeRegister.getProducts()) {
            final String name = product.getName();

            if (products.containsKey(name)) {
                products.put(name, products.get(name).subtract(product.getQuantity()));
            }
        }
    }

    public Map<String, Quantity> getProducts() {
        computeShoppingList();
        return products;
    }

    public boolean contains(String productName) {
        return products.containsKey(productName);
    }
}
