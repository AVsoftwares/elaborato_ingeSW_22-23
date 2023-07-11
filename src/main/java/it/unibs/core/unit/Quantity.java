package it.unibs.core.unit;

import java.util.Objects;
import java.util.Optional;
import java.util.regex.PatternSyntaxException;

/**
 * Classe che rappresenta una quantitò, associata ad una unità di misura e ad un prefisso metrico
 */
public class Quantity {
    /**
     * Unità di misura della quantità
     */
    private final MeasureUnit unit;
    /**
     * Ammontare della quantità
     */
    private float amount;
    /**
     * Prefisso dell'unità di misura
     */
    private MetricPrefix prefix;

    /**
     * Ritorna un'istanza di Quantity
     * @param amount ammontare della quantità
     * @param prefix prefisso dell'unità di misura
     * @param unit unità di misura
     * @throws IllegalArgumentException se amount è minore di 0 (zero)
     */
    public Quantity(float amount, MetricPrefix prefix, MeasureUnit unit) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount must be greater or equal to 0");
        }
        this.amount = amount;
        this.prefix = Objects.requireNonNull(prefix);
        this.unit = Objects.requireNonNull(unit);
    }

    /**
     * Effettua il parsing di una Quantity da una stringa di input
     * @param value la stringa da cui effettua il parsing
     * @return un Optional vuoto nel caso in cui il parsing sia fallito, un Optional contenente la Quantity
     * se il parsing è andato a buon fine
     */
    public static Optional<Quantity> fromString(String value) {
        try {
            final String[] split = value.split(" ");

            final float amount = Float.parseFloat(split[0]);

            if (split.length == 1) {
                return Optional.of(new Quantity(amount, MetricPrefix.NONE, MeasureUnit.UNITS));
            }

            if (split.length == 2) {
                if (split[1].length() == 1) {
                    final MeasureUnit unit = MeasureUnit.fromString(String.valueOf(split[1].charAt(0)));
                    return Optional.of(new Quantity(amount, MetricPrefix.NONE, unit));
                } else if (split[1].length() == 2) {
                    final MetricPrefix prefix = MetricPrefix.fromString(String.valueOf(split[1].charAt(0)));
                    final MeasureUnit unit = MeasureUnit.fromString(String.valueOf(split[1].charAt(1)));
                    return Optional.of(new Quantity(amount, prefix, unit));
                }
            }
            return Optional.empty();
        } catch (PatternSyntaxException | NumberFormatException e) {
            return Optional.empty();
        }
    }

    /**
     * Aggiunge un valore alla Quantity corrente
     * @param value il valore floating point da aggiungere
     * @return l'istanza stessa della classe
     */
    public Quantity add(float value) {
        amount += value;
        return this;
    }

    /**
     * Aggiunge una quantitò alla Quantity corrente
     * @param value la quantità da aggiungere
     * @return @see {@link #add(float) add} metodo
     */
    public Quantity add(Quantity value) {
        value.convertTo(this);
        return add(value.getAmount());
    }

    /**
     * Sottrae un valore alla Quantity corrente
     * @param value il valore floating point da sottrarre
     * @return l'istanza stessa della classe
     */
    public Quantity subtract(float value) {
        amount -= value;
        return this;
    }

    /**
     * Sottrae una quantità alla Quantity corrente
     * @param value la quantità da aggiungere
     * @return @see {@link #subtract(float) subtract} metodo
     */
    public Quantity subtract(Quantity value) {
        value.convertTo(this);
        return subtract(value.getAmount());
    }

    /**
     * Moltiplica un valore alla Quantity corrente
     * @param value il valore floating point da moltiplicare
     * @return l'istanza stessa della classe
     */
    public Quantity multiply(float value) {
        amount *= value;
        return this;
    }
    /**
     * Moltiplica una quantità alla Quantity corrente
     * @param value la quantità da moltiplicare
     * @return @see {@link #multiply(float) multiply} metodo
     */
    public Quantity multiply(Quantity value) {
        value.convertTo(this);
        return multiply(value.getAmount());
    }

    /**
     * Converte il prefisso dell'unità di misura della quantità corrente, in quello della quantità passata come parametro
     * @param quantity la quantità verso cui convertire
     * @throws IllegalArgumentException se le unità di misura del chiamante e dell'argomento sono diverse
     */
    public void convertTo(Quantity quantity) throws IllegalArgumentException {
        if (!unit.equals(quantity.unit)) {
            throw new IllegalArgumentException("Argument must have the same unit of caller class");
        }

        if (prefix.equals(quantity.prefix)) {
            return;
        }

        convertTo(quantity.prefix);
    }

    /**
     * Converte il prefisso dell'unità di misura della quantità corrente, in quella passata come parametro
     * @param prefix il prefisso verso cui convertire
     */
    public void convertTo(MetricPrefix prefix) {
        amount *= 10 * prefix.getExponent();
        this.prefix = prefix;
    }

    public float getAmount() {
        return amount;
    }

    public MetricPrefix getPrefix() {
        return prefix;
    }

    public MeasureUnit getUnit() {
        return unit;
    }

    @Override
    public String toString() {
        return amount + " " + prefix.getSymbol() + unit.getSymbol();
    }
}
