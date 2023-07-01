package it.unibs.core;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
@RequiredArgsConstructor
public class Restaurant {
    private static final float SUSTAINABLE_WORKLOAD_MULTIPLIER = 1.2f;
    private final List<Recipe> recipes;
    private final List<Dish> dishes;
    private final List<ThematicMenu> thematicMenus;
    private final ReservationManager reservationManager;
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
        this.dishes = new ArrayList<>();
        this.thematicMenus = new ArrayList<>();
        this.reservationManager = new ReservationManager(new ArrayList<>());
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

    public boolean canSustainWorkload(float workload) {
        return getAvailableWorkload() - workload >= 0;
    }

    public float getAvailableWorkload() {
        float currWorkload = 0f;

        for (Reservation reservation : reservationManager.getReservations()) {
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

    public boolean addReservation(Reservation reservation) {
        return reservationManager.addReservation(reservation);
    }

    public List<ThematicMenu> getAvailableMenus() {
        return getAvailableMenusAtDate(LocalDate.now());
    }

    public List<ThematicMenu> getAvailableMenusAtDate(LocalDate date) {
        return thematicMenus.stream().filter(m -> !m.isExpiredAtDate(date)).collect(Collectors.toList());
    }

    public List<Dish> getAvailableDishes() {
        return getAvailableDishesAtDate(LocalDate.now());
    }

    public List<Dish> getAvailableDishesAtDate(LocalDate date) {
        return dishes.stream().filter(d -> !d.isExpiredAtDate(date)).collect(Collectors.toList());
    }

    public int getAvailableSeats() {
        return getAvailableSeatsAtDate(LocalDate.now());
    }

    public int getAvailableSeatsAtDate(LocalDate date) {
        return seats - reservationManager.getReservations().stream().filter(b -> b.getDate().isEqual(date)).mapToInt(Reservation::getSeats).sum();
    }

    public ReservationManager getReservationManager() {
        return reservationManager;
    }
}
