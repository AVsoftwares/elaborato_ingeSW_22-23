package it.unibs.core;

import it.unibs.core.unit.Quantity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Product implements Expire {
    private final String name;
    private final LocalDate expiration;
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

    public boolean isAvailable() {
        return quantity.getAmount() > 0;
    }

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

    @Override
    public boolean isValid() {
        return isValidAtDate(LocalDate.now());
    }

    @Override
    public boolean isValidAtDate(LocalDate date) {
        return date.isAfter(expiration);
    }

    @Override
    public String toString() {
        return name + " " + quantity + " " + expiration.format(DateTimeFormatter.ofPattern("dd/MM/yy"));
    }
}
