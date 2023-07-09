package it.unibs.core;

import it.unibs.core.unit.Quantity;

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

    public Map<String, Quantity> getProducts() {
        computeShoppingList();
        return products;
    }

    public boolean contains(String productName) {
        return getProducts().containsKey(productName);
    }

    private void computeShoppingList() {
        for (Reservation reservation : reservationService.getReservations()) {
            computeOrders(reservation);
            computeDrinks(reservation.getSeats());
            computeExtraGroceries(reservation.getSeats());
        }
        removeProductsWithEnoughSupply();
    }

    private void removeProductsWithEnoughSupply() {
        for (Product storedProduct : storeRegister.getProducts()) {
            final String name = storedProduct.getName();

            if (products.containsKey(name)) {
                products.put(name, products.get(name).subtract(storedProduct.getQuantity()));
            }
        }

        products.entrySet().removeIf(e -> e.getValue().getAmount() <= 0);
    }

    private void computeOrders(Reservation reservation) {
        final Map<Orderable, Integer> orders = reservation.getOrders();


        orders.forEach((orderable, count) -> {
            final Map<? extends Product, Quantity> productsQuantity = orderable.getProductsQuantity();

            productsQuantity.forEach((product, quantity) -> {
                final String name = product.getName();
                final Quantity totalQuantity = new Quantity(quantity.getAmount() * count, quantity.getPrefix(), quantity.getUnit());

                if (products.containsKey(name)) {
                    products.put(name, totalQuantity.add(products.get(name)));
                } else {
                    products.put(name, totalQuantity);
                }
            });
        });
    }

    private void computeDrinks(int numberOfPeople) {
        final Map<String, Quantity> averageDrinkConsumption = restaurant.getImmutableAverageDrinkConsumptionNotNull();

        averageDrinkConsumption.forEach((drink, quantity) -> {
            final Quantity totalQuantity = quantity.multiply(numberOfPeople);

            if (products.containsKey(drink)) {
                products.put(drink, products.get(drink).add(totalQuantity));
            } else {
                products.put(drink, totalQuantity);
            }
        });
    }

    private void computeExtraGroceries(int numberOfPeople) {
        final Map<String, Quantity> averageExtraConsumption = restaurant.getImmutableAverageExtraConsumptionNotNull();

        averageExtraConsumption.forEach((extra, quantity) -> {
            final Quantity totalQuantity = quantity.multiply(numberOfPeople);

            if (products.containsKey(extra)) {
                products.put(extra, products.get(extra).add(totalQuantity));
            } else {
                products.put(extra, totalQuantity);
            }
        });
    }
}
