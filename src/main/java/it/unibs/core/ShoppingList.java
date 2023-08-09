package it.unibs.core;

import it.unibs.core.unit.Quantity;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Lista della spesa che il gestore del magazzino può consultare per gli acquisti
 */
public class ShoppingList {
    /**
     * Map di coppie prodotto-quantità che bisogna acquistare per soddisfare la domanda
     */
    private final Map<String, Quantity> products = new HashMap<>();
    /**
     * Referenza al ristorante
     * @see Restaurant
     */
    private final Restaurant restaurant;
    /**
     * Referenza allo store register
     * @see StoreRegister
     */
    private final StoreRegister storeRegister;
    /**
     * Referenza al reservation service
     * @see ReservationService
     */
    private final ReservationService reservationService;

    public ShoppingList(StoreRegister storeRegister, ReservationService reservationService) {
        this.restaurant = Restaurant.getInstance();
        this.storeRegister = Objects.requireNonNull(storeRegister);
        this.reservationService = Objects.requireNonNull(reservationService);
    }

    /**
     * @return la Map prodotto-quantità da acquistare per soddisfare la domanda
     */
    public Map<String, Quantity> getProducts() {
        computeShoppingList();
        return products;
    }

    /**
     * @param productName nome del prodotto
     * @return true se la lista della spesa contiene il prodotto specificato
     */
    public boolean contains(String productName) {
        return getProducts().containsKey(productName);
    }

    /**
     * Computa la lista {@link #products} della spesa tenendo conto per ciascuna prenotazione dei piatti e dei menu prenotati,
     * delle bevande, dei generi alimentari extra e del relativo consumo pro-capite
     * Rimuove infine i prodotti che hanno in magazzino quantità sufficiente
     */
    private void computeShoppingList() {
        for (Reservation reservation : reservationService.getReservations()) {
            computeOrders(reservation);
            computeDrinks(reservation.getSeats());
            computeExtraGroceries(reservation.getSeats());
        }
        removeProductsWithEnoughSupply();
    }

    /**
     * Rimuove dalla lista {@link #products} i prodotti che hanno in magazzino quantità sufficiente a soddisfare la domanda
     */
    private void removeProductsWithEnoughSupply() {
        for (Product storedProduct : storeRegister.getProducts()) {
            final String name = storedProduct.getName();

            if (products.containsKey(name)) {
                products.put(name, products.get(name).subtract(storedProduct.getQuantity()));
            }
        }

        products.entrySet().removeIf(e -> e.getValue().getAmount() <= 0);
    }

    /**
     * Computa quali prodotti sono necessari per la prenotazione specificata
     * @param reservation la prenotazione per la quale bisogna calcolare gli ordini
     */
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

    /**
     * Computa quali e quanti drink sono necessari per il numero di persone specificate
     * @param numberOfPeople numero di persone della prenotazione
     */
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

    /**
     * Computa quali e quanti alimenti extra sono necessari per il numero di persone specificate
     * @param numberOfPeople numero di persone della prenotazione
     */
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
