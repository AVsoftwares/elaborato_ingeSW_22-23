package it.unibs.core;

import java.time.LocalDate;
import java.util.List;

public class ThematicMenu extends Menu implements Expire {

    private final Period period;

    /**
     * @param name nome del menu tematico
     */

    public ThematicMenu(String name, Period period, List<Dish> dishes) {
        super(name, dishes);
        this.period = period;
    }

    /**
     * @return carico totale di lavoro relativo al menu tematico
     */
    public float getTotalWorkload() {
        return (float) super.getDishes().stream().mapToDouble(Dish::getWorkload).sum();
    }

    @Override
    public boolean isExpired() {
        return isExpiredAtDate(LocalDate.now());
    }

    @Override
    public boolean isExpiredAtDate(LocalDate date) {
        return period.isBefore(date);
    }

    @Override
    public boolean add(Dish dish) {
        return super.add(dish);
    }

    @Override
    public String toString() {
        return super.toString() + period;
    }
}
