package it.unibs.ui.manager.commands;

import it.unibs.core.Ingredient;
import it.unibs.core.Recipe;
import it.unibs.core.Restaurant;
import it.unibs.core.unit.Quantity;
import it.unibs.ui.BaseMenu;
import it.unibs.ui.Command;
import it.unibs.ui.InputManager;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class RecipesCommand implements Command {

    public static final String VIEW_RECIPES = "Visualizza ricette";
    public static final String NO_RECIPES_SAVED = "Non sono memorizzate ricette.";
    public static final String ADD_RECIPE = "Aggiungi ricetta";
    public static final String INGREDIENT_NAME = "Nome dell'ingrediente: ";
    public static final String INGREDIENT_AMOUNT = "Quantità dell'ingrediente (es. 10 kg): ";
    public static final String AMOUNT_NOT_VALID = """
            La quantità inserita non è valida.
            Deve essere nel formato: quantity [prefix unit]
            Le unità di misura accettate sono (l)itri e (g)rammi, se omessa si considerano le unità""";
    public static final String ANOTHER_INGREDIENT_Y_OR_NO = "Vuoi inserire un altro ingrediente? (y)es/(n)o: ";
    public static final String PORTIONS_PREPARED_WITH_INSERTED_DOSES = "Numero di porzioni che possono essere preparate con le dosi inserite: ";
    public static final String PORTION_WORKLOAD = "Carico di lavoro per porzione: ";
    private final Restaurant restaurant = Restaurant.getInstance();

    @Override
    public void execute() {
        BaseMenu menu = new BaseMenu("Gestione ricette");

        final List<Recipe> recipes = restaurant.getRecipes();

        menu.addEntry(VIEW_RECIPES, () -> {
            if (recipes.isEmpty()) {
                System.out.println(NO_RECIPES_SAVED);
            } else {
                recipes.forEach(System.out::println);
            }
        });
        menu.addEntry(ADD_RECIPE, () -> {

            final HashMap<Ingredient, Quantity> ingredients = new HashMap<>();

            do {
                final String name = InputManager.readString(INGREDIENT_NAME);
                final Optional<Quantity> amount = Quantity.fromString(InputManager.readString(INGREDIENT_AMOUNT));

                if (amount.isPresent()) {
                    ingredients.put(new Ingredient(name), amount.get());
                } else {
                    System.out.println(AMOUNT_NOT_VALID);
                }
            } while (InputManager.readYesOrNo(ANOTHER_INGREDIENT_Y_OR_NO));

            final int portions = InputManager.readInt(PORTIONS_PREPARED_WITH_INSERTED_DOSES, 1, Integer.MAX_VALUE);
            final float portionWorkload = InputManager.readFloat(PORTION_WORKLOAD, 0, 1);

            recipes.add(new Recipe(ingredients, portions, portionWorkload));
        });

        menu.run();
    }
}
