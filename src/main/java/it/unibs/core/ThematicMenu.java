package it.unibs.core;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ThematicMenu extends Menu {

    private LocalDate startDate;
    private LocalDate expireDate;

    /**
     * @param name nome del menu tematico
     */

    public ThematicMenu(String name) {
        super(name);
    }

    /**
     * @return carico totale di lavoro relativo al menu tematico
     */
    public int getTotalWorkload() {
        return super.getDishes().stream().mapToInt(Dish::getWorkload).sum();
    }

    @Override
    public String toString() {
        final var startDateString = startDate.format(DateTimeFormatter.ofPattern("dd/MM/yy"));
        final var expireDateString = expireDate.format(DateTimeFormatter.ofPattern("dd/MM/yy"));

        return super.toString() + "\n\tValido dal " + startDateString + " al " + expireDateString;
    }
}
