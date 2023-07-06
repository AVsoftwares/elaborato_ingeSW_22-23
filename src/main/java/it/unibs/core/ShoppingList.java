package it.unibs.core;

import it.unibs.core.unit.Quantity;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ShoppingList {
    private final Map<String, Quantity> products = new HashMap<>();
    private final Restaurant restaurant;
    private final StoreRegister storeRegister;
    private final ReservationService reservationService;

    public ShoppingList(Restaurant restaurant, StoreRegister storeRegister, ReservationService reservationService) {
        this.restaurant = Objects.requireNonNull(restaurant);
        this.storeRegister = Objects.requireNonNull(storeRegister);
        this.reservationService = Objects.requireNonNull(reservationService);
    }

    private void computeShoppingList() {
        final Map<String, Quantity> averageDrinkConsumption = restaurant.getImmutableAverageDrinkConsumptionNotNull();
        final Map<String, Quantity> averageExtraConsumption = restaurant.getImmutableAverageExtraConsumptionNotNull();

        for (Reservation reservation : reservationService.getReservations(LocalDate.now())) {
            final Map<Dish, Integer> dishBookings = reservation.getDishes();
            final Map<ThematicMenu, Integer> thematicMenuBookings = reservation.getThematicMenus();

            dishBookings.forEach((dish, count) -> {
                final Map<Ingredient, Quantity> ingredients = dish.getRecipe().getIngredients();

                ingredients.forEach((ingredient, quantity) -> {
                    final String name = ingredient.getName();
                    final Quantity totalQuantity = new Quantity(quantity.getAmount() * count, quantity.getPrefix(), quantity.getUnit());

                    if (products.containsKey(name)) {
                        products.put(name, totalQuantity.add(products.get(name)));
                    } else {
                        products.put(name, totalQuantity);
                    }
                });
            });

            thematicMenuBookings.forEach((menu, count) -> {
                for (Dish dish : menu.getDishes()) {
                    final Map<Ingredient, Quantity> ingredients = dish.getRecipe().getIngredients();

                    ingredients.forEach((ingredient, quantity) -> {
                        final String name = ingredient.getName();
                        final Quantity totalQuantity = new Quantity(quantity.getAmount() * count, quantity.getPrefix(), quantity.getUnit());

                        if (products.containsKey(name)) {
                            products.put(name, totalQuantity.add(products.get(name)));
                        } else {
                            products.put(name, totalQuantity);
                        }
                    });
                }
            });

            averageDrinkConsumption.forEach((drink, quantity) -> {
                if (products.containsKey(drink)) {
                    products.put(drink, products.get(drink).add(quantity));
                } else {
                    products.put(drink, quantity);
                }
            });

            averageExtraConsumption.forEach((extra, quantity) -> {
                if (products.containsKey(extra)) {
                    products.put(extra, products.get(extra).add(quantity));
                } else {
                    products.put(extra, new Quantity(quantity.getAmount(), quantity.getPrefix(), quantity.getUnit()));
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
