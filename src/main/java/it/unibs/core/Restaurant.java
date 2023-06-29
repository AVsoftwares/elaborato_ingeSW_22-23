package it.unibs.core;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Restaurant {
    private final List<Recipe> recipes;
    private final List<Dish> dishes;
    private final List<ThematicMenu> thematicMenus;
    private final List<Reservation> reservations;
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

    private static final float SUSTAINABLE_WORKLOAD_MULTIPLIER = 1.2f;

    public Restaurant() {
        this.recipes = new ArrayList<>();
        this.dishes = new ArrayList<>();
        this.thematicMenus = new ArrayList<>();
        this.reservations = new ArrayList<>();
        this.avgDrinkAmount = new HashMap<>();
        this.avgExtraAmount = new HashMap<>();
        this.seats = 0;
        this.individualWorkload = 0;
    }

    public Restaurant(File file) throws IOException {
        // TODO questa è solo un'idea

        //oppure ServizioFile.caricaSingoloOggetto(File configData);?
        //System.getProperties().load(new FileInputStream(file));
        //this.name = System.getProperty("name");
        this();
    }

    private float getSustainableWorkload() {
        return (individualWorkload * seats) * SUSTAINABLE_WORKLOAD_MULTIPLIER;
    }

    public float getAvailableWorkload() {
        float currWorkload = 0f;

        for (Reservation reservation : reservations) {
            currWorkload += reservation.getWorkload();
        }

        return getSustainableWorkload() - currWorkload;
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

    public void addBooking(Reservation reservation) {
        reservations.add(reservation);
    }

    public List<ThematicMenu> getAvailableMenus() {
        return getAvailableMenusAtDate(LocalDate.now());
    }

    public List<ThematicMenu> getAvailableMenusAtDate(LocalDate date) {
        return thematicMenus.stream().filter(m -> m.isAvailableAtDate(date)).collect(Collectors.toList());
    }

    public List<Dish> getAvailableDishes() {
        return getAvailableDishesAtDate(LocalDate.now());
    }

    public List<Dish> getAvailableDishesAtDate(LocalDate date) {
        return dishes.stream().filter(d -> d.isAvailableAtDate(date)).collect(Collectors.toList());
    }

    public int getAvailableSeats() {
        return getAvailableSeatsAtDate(LocalDate.now());
    }

    public int getAvailableSeatsAtDate(LocalDate date) {
        return seats - reservations.stream().filter(b -> b.getDate().isEqual(date)).mapToInt(Reservation::getSeats).sum();
    }

    public List<Reservation> getReservations() {
        reservations.removeIf(Reservation::isExpired);
        
        return reservations;
    }
}
