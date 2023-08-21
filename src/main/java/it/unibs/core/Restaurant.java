package it.unibs.core;

import it.unibs.core.unit.Quantity;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Restaurant implements Subscriber{

    /**
     * Costante moltiplicativa del carico di lavoro sostenibile dal ristorante
     */
    private static final float SUSTAINABLE_WORKLOAD_MULTIPLIER = 1.2f;
    private volatile static Restaurant restaurantInstance;
    /**
     * Lista di ricette memorizzate nel ristorante
     */
    private final List<Recipe> recipes;
    /**
     * Set di piatti memorizzati nel ristorante
     */
    private final Set<Dish> dishes;
    /**
     * Set di menu tematici memorizzati nel ristorante
     */
    private final Set<ThematicMenu> thematicMenus;
    /**
     * Map che associa il nome della bevanda al corrispettivo ammontare di consumo tipico procapite in litri
     */
    private final Map<String, Quantity> averageDrinkConsumption;
    /**
     * Map che associa il nome del genere extra al corrispettivo ammontare di consumo tipico procapite in ettogrammi
     */
    private final Map<String, Quantity> averageExtraConsumption;
    /**
     * Numero di posti a sedere del ristorante
     */
    private int seats;
    /**
     * Carico di lavoro individuale sostenibile dal ristorante
     */
    private int individualWorkload;

    /*Singleton-> Costruttore privato*/

    private Restaurant() {
        this.recipes = new ArrayList<>();
        this.dishes = new HashSet<>();
        this.thematicMenus = new HashSet<>();
        this.averageDrinkConsumption = new HashMap<>();
        this.averageExtraConsumption = new HashMap<>();
    }

    public static Restaurant getInstance() {
        if (restaurantInstance == null) {
            synchronized (Restaurant.class) {
                if (restaurantInstance == null) {
                    restaurantInstance = new Restaurant();
                }
            }
        }
        return restaurantInstance;
    }

    /**
     * Calcola il carico di lavoro sostenibile dal ristorante
     *
     * @return carico di lavoro sostenibile
     */
    public float getSustainableWorkload() {
        return (individualWorkload * seats) * SUSTAINABLE_WORKLOAD_MULTIPLIER;
    }

    /**
     * getter che ritorna la lista di menu disponibili alla data in input
     *
     * @param date data rispetto alla quale si vuole ottenere la lista di menu disponibili
     * @return lista di menu disponibili alla data in input
     */
    public List<ThematicMenu> getAvailableMenus(LocalDate date) {
        return thematicMenus.stream().filter(m -> m.isExpiredAtDate(date) && m.isAvailable()).toList();
    }

    /**
     * getter che ritorna la lista di piatti disponibili alla data in input
     *
     * @param date data rispetto alla quale si vuole ottenere la lista di piatti disponibili
     * @return lista di piatti disponibili alla data in input
     */
    public List<Dish> getAvailableDishes(LocalDate date) {
        return dishes.stream().filter(d -> d.isExpiredAtDate(date)&& d.isAvailable()).toList();
    }

    /**
     * getter che ritorna un piatto ricercandolo nella lista tramite il nome dello stesso
     *
     * @param name nome del piatto da cercare
     * @return un {@link Optional} vuoto se non esiste piatto con tale nome, {@link Optional} contenente il piatto
     * se esiste nella lista un piatto con tale nome
     */
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

    /**
     * Controlla se il consumo medio di un genere alimentare extra non è stato ancora inizializzato
     *
     * @param name il nome del genere alimentare extra
     * @return true se il consumo medio del genere alimentare extra è null, false altrimenti
     */
    public boolean isAverageExtraConsumptionNotSet(String name) {
        return averageExtraConsumption.get(name.toLowerCase()) == null;
    }

    /**
     * Ritorna una Map immutabile delle coppie extra-quantità in modo da proteggere uso improprio dai client
     *
     * @return Map immputabile di coppie extra-quantità
     */
    public Map<String, Quantity> getImmutableAverageExtraConsumption() {
        return Collections.unmodifiableMap(averageExtraConsumption);
    }

    /**
     * Ritorna una Map immutabile delle coppie extra-quantità che sono state inizializzate (quantità != null)
     *
     * @return Map immputabile di coppie extra-quantità inizializzate
     */
    public Map<String, Quantity> getImmutableAverageExtraConsumptionNotNull() {
        return averageExtraConsumption.entrySet().stream()
                .filter(e -> e.getValue() != null)
                .collect(Collectors.toUnmodifiableMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * Inizializza il consumo di una coppia extra-quantità
     *
     * @param name   il nome dell'alimento extra
     * @param amount la quantità a cui lo si vuole inizializzare
     * @throws IllegalStateException se l'alimento extra non è presente nella lista
     */
    public void setAverageExtraConsumption(String name, Quantity amount) {
        averageExtraConsumption.put(name.toLowerCase(), amount);
    }

    /**
     * Controlla se il consumo medio di una bevanda non è stato ancora inizializzato
     *
     * @param name il nome della bevanda
     * @return true se il consumo medio della bevanda è null, false altrimenti
     */
    public boolean isAverageDrinkConsumptionNotSet(String name) {
        return averageDrinkConsumption.get(name.toLowerCase()) == null;
    }

    /**
     * Ritorna una Map immutabile delle coppie bevanda-quantità in modo da proteggere uso improprio dai client
     *
     * @return Map immputabile di coppie bevanda-quantità
     */
    public Map<String, Quantity> getImmutableAverageDrinkConsumption() {
        return Collections.unmodifiableMap(averageDrinkConsumption);
    }

    /**
     * Ritorna una Map immutabile delle coppie bevanda-quantità che sono state inizializzate (quantità != null)
     *
     * @return Map immputabile di coppie bevanda-quantità inizializzate
     */
    public Map<String, Quantity> getImmutableAverageDrinkConsumptionNotNull() {
        return averageDrinkConsumption.entrySet().stream()
                .filter(e -> e.getValue() != null)
                .collect(Collectors.toUnmodifiableMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * Inizializza il consumo di una coppia bevanda-quantità
     *
     * @param name   il nome della bevanda
     * @param amount la quantità a cui la si vuole inizializzare
     * @throws IllegalStateException se la bevanda non è presente nella lista
     */
    public void setAverageDrinkConsumption(String name, Quantity amount) {
        averageDrinkConsumption.put(name.toLowerCase(), amount);
    }

    /**
     * Aggiunge una bevanda, con quantità nulla
     *
     * @param name nome della bevanda
     */
    public void addDrink(String name) {
        averageDrinkConsumption.putIfAbsent(name.toLowerCase(), null);
    }


    /**
     * Aggiunge un alimento extra, con quantità nulla
     *
     * @param name nome dell'alimento extra
     */
    public void addExtra(String name) {
        averageExtraConsumption.putIfAbsent(name.toLowerCase(), null);
    }


    @Override
    public <T extends Publisher> void update(T context) {
        StoreRegister register = (StoreRegister) context;

        List<Product> products = register.getProducts();

        for (Product p: products){

            for (Dish d: dishes) {
                for (Ingredient i : d.getRecipe().getIngredients().keySet()) {
                    if (!p.equals(i)) {
                        d.setAvailable(false);
                    }
                }
               d.setAvailable(true);
            }

            for (Menu m : thematicMenus) {
                for (Dish d: m.getDishes()){
                    for (Ingredient i: d.getRecipe().getIngredients().keySet()) {
                        if(!p.equals(i)){
                            d.setAvailable(false);
                        }
                    }
                    d.setAvailable(true);
                }
                m.setAvailable(true);
            }
        }

    }
}
