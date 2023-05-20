package it.unibs.core;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import lombok.Getter;
import lombok.Setter;

/**
 * Menu tematico è stabilito dal gestore e costituito da un elenco di piatti
 * destinati a essere
 * ordinati dal cliente tutti insieme per una persona. Può non essere
 * disponibile per tutto
 * l’arco dell’anno. La somma del carico di lavoro di tutti i piatti contenuti,
 * detta carico di
 * lavoro del menu tematico, deve essere minore o uguale ai 4/3 del carico di
 * lavoro per
 * persona. Possono coesistere più menu tematici, anche contemporaneamente
 * validi.
 */
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
