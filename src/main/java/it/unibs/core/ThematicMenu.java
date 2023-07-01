package it.unibs.core;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ThematicMenu extends Menu implements Expire {

    private final Period period;
    private final int maxIndividualWorkload;

    /**
     * @param name nome del menu tematico
     */

    public ThematicMenu(String name, Period period, int maxIndividualWorkload) {
        super(name);
        this.period = period;
        this.maxIndividualWorkload = maxIndividualWorkload;
    }

    /**
     * @return carico totale di lavoro relativo al menu tematico
     */
    public int getTotalWorkload() {
        return super.getDishes().stream().mapToInt(Dish::getWorkload).sum();
    }

    public boolean isDishCompatible(Dish dish) {
        return getTotalWorkload() + dish.getWorkload() < (float) 4 / 3 * maxIndividualWorkload;
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
    public boolean addDish(Dish dish) {
        return super.addDish(dish);
    }

    @Override
    public String toString() {
        return super.toString() + period;
    }
}
