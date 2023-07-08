package it.unibs.core;

import it.unibs.core.unit.Quantity;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Restaurant {
    private static final float SUSTAINABLE_WORKLOAD_MULTIPLIER = 1.2f;
    // TODO recipes potrebbe essere una classe indipendente RecipeBook
    private final List<Recipe> recipes;
    private final Set<Dish> dishes;
    private final Set<ThematicMenu> thematicMenus;
    /**
     * Map che associa il nome della bevanda al corrispettivo ammontare di consumo tipico procapite in litri
     */
    private final Map<String, Quantity> averageDrinkConsumption;
    /**
     * Map che associa il nome del genere extra al corrispettivo ammontare di consumo tipico procapite in ettogrammi
     */
    private final Map<String, Quantity> averageExtraConsumption;
    private int seats;
    private int individualWorkload;

    public Restaurant() {
        this.recipes = new ArrayList<>();
        this.dishes = new HashSet<>();
        this.thematicMenus = new HashSet<>();
        this.averageDrinkConsumption = new HashMap<>();
        this.averageExtraConsumption = new HashMap<>();
    }

    public float getSustainableWorkload() {
        return (individualWorkload * seats) * SUSTAINABLE_WORKLOAD_MULTIPLIER;
    }

    public List<ThematicMenu> getAvailableMenus(LocalDate date) {
        return thematicMenus.stream().filter(m -> m.isValidAtDate(date)).toList();
    }

    public List<Dish> getAvailableDishes(LocalDate date) {
        return dishes.stream().filter(d -> d.isValidAtDate(date)).toList();
    }

    public Optional<Dish> getDish(String name) {
        for (Dish dish : dishes) {
            if (dish.getName().equalsIgnoreCase(name)) {
                return Optional.of(dish);
            }
        }
        return Optional.empty();
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public Set<Dish> getDishes() {
        return dishes;
    }

    public Set<ThematicMenu> getThematicMenus() {
        return thematicMenus;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public int getIndividualWorkload() {
        return individualWorkload;
    }

    public void setIndividualWorkload(int individualWorkload) {
        this.individualWorkload = individualWorkload;
    }

    public boolean isAverageExtraConsumptionNotSet(String name) {
        return averageExtraConsumption.get(name) == null;
    }

    public Map<String, Quantity> getImmutableAverageExtraConsumption() {
        return Collections.unmodifiableMap(averageExtraConsumption);
    }

    public Map<String, Quantity> getImmutableAverageExtraConsumptionNotNull() {
        return averageExtraConsumption.entrySet().stream()
                .filter(e -> e.getValue() != null)
                .collect(Collectors.toUnmodifiableMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public void setAverageExtraConsumption(String name, Quantity amount) {
        if (averageExtraConsumption.containsKey(name)) {
            throw new IllegalStateException("Consumption for \"" + name + "\" is not yet set");
        }
        averageExtraConsumption.put(name.toLowerCase(), amount);
    }

    public boolean isAverageDrinkConsumptionNotSet(String name) {
        return averageDrinkConsumption.get(name) == null;
    }

    public Map<String, Quantity> getImmutableAverageDrinkConsumption() {
        return Collections.unmodifiableMap(averageDrinkConsumption);
    }

    public Map<String, Quantity> getImmutableAverageDrinkConsumptionNotNull() {
        return averageDrinkConsumption.entrySet().stream()
                .filter(e -> e.getValue() != null)
                .collect(Collectors.toUnmodifiableMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public void setAverageDrinkConsumption(String name, Quantity amount) throws IllegalStateException {
        if (averageDrinkConsumption.containsKey(name)) {
            throw new IllegalStateException("Consumption for \"" + name + "\" is not yet set");
        }
        averageDrinkConsumption.put(name.toLowerCase(), amount);
    }

    public void addDrink(String name) {
        averageDrinkConsumption.putIfAbsent(name.toLowerCase(), null);
    }

    public void addExtra(String name) {
        averageExtraConsumption.putIfAbsent(name.toLowerCase(), null);
    }
}
