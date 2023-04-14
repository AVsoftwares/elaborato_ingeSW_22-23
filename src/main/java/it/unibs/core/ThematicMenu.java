package it.unibs.core;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Menu tematico è stabilito dal gestore e costituito da un elenco di piatti destinati a essere
 * ordinati dal cliente tutti insieme per una persona. Può non essere disponibile per tutto
 * l’arco dell’anno. La somma del carico di lavoro di tutti i piatti contenuti, detta carico di
 * lavoro del menu tematico, deve essere minore o uguale ai 4/3 del carico di lavoro per
 * persona. Possono coesistere più menu tematici, anche contemporaneamente validi.
 */
@Getter
@Setter
public class ThematicMenu extends Menu {

    private String menuName;
    private LocalDate startDate;
    private LocalDate expireDate;

    private int menuWorkLoad = 0;

    public ThematicMenu(String name) {
        super(name);
        this.menuType = MenuType.THEMATIC;
    }

    private void setStartDate(int year, int month, int day) {
        this.startDate = LocalDate.of(year, month, day);
    }

    private void computePortionWorkLoad() {
        for (Dish d : dishList) {
            menuWorkLoad += d.getDishPortionWorkLoad();
        }
    }

    public int getMenuWorkLoad() {
        computePortionWorkLoad();
        return menuWorkLoad;
    }
}
