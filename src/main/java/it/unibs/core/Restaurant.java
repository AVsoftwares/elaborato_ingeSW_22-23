package it.unibs.core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Restaurant {
    private final List<Recipe> recipes = new ArrayList<>();
    private final List<Dish> dishes = new ArrayList<>();
    private final List<Menu> menus = new ArrayList<>();
    /**
     * Map che associa il nome della bevanda al corrispettivo ammontare di consumo tipico procapite
     */
    private final Map<String, Integer> avgDrinkAmount = new HashMap<>();
    /**
     * Map che associa il nome del genere extra al corrispettivo ammontare di consumo tipico procapite
     */
    private final Map<String, Integer> avgExtraAmount = new HashMap<>();
    private int seats = 0;
    private int individualWorkload = 0;

    private static final float SUSTAINABLE_WORKLOAD_MULTIPLIER = 1.2f;

    public Restaurant(File file) throws IOException {
        // TODO questa è solo un'idea

        //oppure ServizioFile.caricaSingoloOggetto(File configData);?
        //System.getProperties().load(new FileInputStream(file));
        //this.name = System.getProperty("name");
    }

    /**
     * @return carico di lavoro sostenibile dal ristorante
     */
    private float getSustainableWorkload() {
        return (individualWorkload * seats) * SUSTAINABLE_WORKLOAD_MULTIPLIER;
    }

    /**
     * @param recipes aggiunta della ricetta al ricettario del ristorante
     */
    public void addRecipe(Recipe... recipes) {
        Collections.addAll(this.recipes, recipes);
    }

    /**
     * @param menus aggiunta del menu tematico ai menu tematici disponibili del ristorante
     */
    public void addMenu(Menu... menus) {
        Collections.addAll(this.menus, menus);
    }

    /**
     * @param dishes aggiunta del piatto ai piatti disponibili del ristorante
     */
    public void addDish(Dish... dishes) {
        Collections.addAll(this.dishes, dishes);
    }
}
