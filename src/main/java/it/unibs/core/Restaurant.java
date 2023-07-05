package it.unibs.core;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@RequiredArgsConstructor
public class Restaurant {
    private static final float SUSTAINABLE_WORKLOAD_MULTIPLIER = 1.2f;
    // TODO recipes potrebbe essere una classe indipendente RecipeBook
    private final List<Recipe> recipes;
    private final Set<Dish> dishes;
    private final List<ThematicMenu> thematicMenus;
    /**
     * Map che associa il nome della bevanda al corrispettivo ammontare di consumo tipico procapite in litri
     */
    private final Map<String, Float> avgDrinkAmount;
    /**
     * Map che associa il nome del genere extra al corrispettivo ammontare di consumo tipico procapite in ettogrammi
     */
    private final Map<String, Float> avgExtraAmount;
    private int seats;
    private int individualWorkload;

    public Restaurant() {
        this.recipes = new ArrayList<>();
        this.dishes = new HashSet<>();
        this.thematicMenus = new ArrayList<>();
        this.avgDrinkAmount = new HashMap<>();
        this.avgExtraAmount = new HashMap<>();
    }

    public Restaurant(File file) throws IOException {
        // TODO questa è solo un'idea

        //oppure ServizioFile.caricaSingoloOggetto(File configData);?
        //System.getProperties().load(new FileInputStream(file));
        //this.name = System.getProperty("name");
        this();
    }

    public float getSustainableWorkload() {
        return (individualWorkload * seats) * SUSTAINABLE_WORKLOAD_MULTIPLIER;
    }

    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

    public void addMenu(ThematicMenu menu) {
        thematicMenus.add(menu);
    }

    public boolean addDish(Dish dish) {
        return dishes.add(dish);
    }

    public List<ThematicMenu> getAvailableMenus() {
        return getAvailableMenus(LocalDate.now());
    }

    public List<ThematicMenu> getAvailableMenus(LocalDate date) {
        return thematicMenus.stream().filter(m -> !m.isExpiredAtDate(date)).collect(Collectors.toList());
    }

    public List<Dish> getAvailableDishes() {
        return getAvailableDishes(LocalDate.now());
    }

    public List<Dish> getAvailableDishes(LocalDate date) {
        return dishes.stream().filter(d -> !d.isExpiredAtDate(date)).collect(Collectors.toList());
    }

    public Optional<Dish> getDish(String name) {
        for (Dish dish : dishes) {
            if (dish.getName().equalsIgnoreCase(name)) {
                return Optional.of(dish);
            }
        }
        return Optional.empty();
    }
}
