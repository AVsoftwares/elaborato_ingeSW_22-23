package it.unibs.core;

import it.unibs.core.unit.Quantity;

import java.time.Clock;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Objects;

/**
 * Rappresenta un generico prodotto presente nel ristorante, può essere: bevanda,
 * genere alimentare extra, ingrediente ed eventualmente altro non contemplato dalla
 * specifica iniziale
 */
public class Product implements Perishable {
    /**
     * Nome del prodotto
     */
    private final String name;
    /**
     * Data di scadenza del prodotto
     */
    private final LocalDate expiration;
    /**
     * Quantità presente del prodotto
     */
    private final Quantity quantity;

    public Product(String name, LocalDate expiration, Quantity quantity) {
        this.name = Objects.requireNonNull(name);
        this.expiration = expiration;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public LocalDate getExpiration() {
        return expiration;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    /**
     * Metodo per controllare se il prodotto è presente in termini di quantità
     *
     * @return true se la quantità è maggiore di 0, false altrimenti
     */
    public boolean isAvailable() {
        return quantity != null && quantity.getAmount() > 0;
    }

    /**
     * Metodo per verificare se due Product sono equivalenti
     *
     * @param o oggetto rispetto al quale viene verificata l'equivalenza
     * @return true se i due oggetti sono riferimenti alla stessa istanza o se entrambi sono istanze di
     * Product ed hanno nome uguale (case insensitive) secondo {@link String#equals(Object) String.equals} e data di
     * scadenza uguale secondo {@link LocalDate#isEqual(ChronoLocalDate) LocalDate.isEqual}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return name.equalsIgnoreCase(product.getName()) && expiration.isEqual(product.getExpiration());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /**
     * Metodo per controllare la validità di un Product, un product è valido se non è scaduto rispetto ad una data
     *
     * @return true se non è scaduto alla data corrente
     * @see #isExpiredAtDate(LocalDate)
     */
    @Override
    public boolean isExpired(Clock clock) {
        return isExpiredAtDate(LocalDate.now(clock));
    }

    /**
     * Metodo per controllare la validità di un Product, un product è valido se non è scaduto rispetto ad una data
     *
     * @param date la data rispetto alla quale controllare la validità
     * @return true se la data passata come parametro è precedente o uguale alla data di scadenza
     */
    @Override
    public boolean isExpiredAtDate(LocalDate date) {
        return date.isAfter(expiration);
    }
}
