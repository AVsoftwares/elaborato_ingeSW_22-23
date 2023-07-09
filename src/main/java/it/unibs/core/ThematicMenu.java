package it.unibs.core;

import it.unibs.core.unit.Quantity;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class ThematicMenu extends Menu implements Orderable, Expire {

    private final Period period;

    /**
     * @param name nome del menu tematico
     */
    public ThematicMenu(String name, Period period, List<Dish> dishes) {
        super(name, dishes);
        this.period = Objects.requireNonNull(period);
    }

    @Override
    public Map<? extends Product, Quantity> getProductsQuantity() {
        return getDishes().stream()
                .flatMap(dish -> dish.getProductsQuantity().entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * @return carico totale di lavoro relativo al menu tematico
     */
    @Override
    public float getWorkload() {
        return (float) super.getDishes().stream().mapToDouble(Dish::getWorkload).sum();
    }

    @Override
    public boolean isValid() {
        return isValidAtDate(LocalDate.now());
    }

    @Override
    public boolean isValidAtDate(LocalDate date) {
        return period.includes(date);
    }

    @Override
    public boolean add(Dish dish) {
        return super.add(dish);
    }

    @Override
    public String toString() {
        return super.toString() + " " + period;
    }
}
