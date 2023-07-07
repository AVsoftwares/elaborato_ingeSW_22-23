package it.unibs.ui.manager.commands;

import it.unibs.core.Ingredient;
import it.unibs.core.Recipe;
import it.unibs.core.Restaurant;
import it.unibs.core.unit.Quantity;
import it.unibs.ui.Command;
import it.unibs.ui.InputManager;
import it.unibs.ui.Menu;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class RecipesCommand implements Command {

    private final Restaurant restaurant;

    public RecipesCommand(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void execute() {
        Menu menu = new Menu("Gestione ricette");

        final List<Recipe> recipes = restaurant.getRecipes();

        menu.addEntry("Visualizza ricette", () -> {
            if (recipes.isEmpty()) {
                System.out.println("Non sono memorizzate ricette.");
            } else {
                recipes.forEach(System.out::println);
            }
        });
        menu.addEntry("Aggiungi ricetta", () -> {

            final HashMap<Ingredient, Quantity> ingredients = new HashMap<>();

            do {
                final String name = InputManager.readString("Nome dell'ingrediente: ");
                final Optional<Quantity> amount = Quantity.fromString(InputManager.readString("Quantità dell'ingrediente (es. 10 kg): "));

                if (amount.isPresent()) {
                    ingredients.put(new Ingredient(name), amount.get());
                } else {
                    System.out.println("""
                            La quantità inserita non è valida.
                            Deve essere nel formato: quantity [prefix unit]
                            Le unità di misura accettate sono (l)itri e (g)rammi, se omessa si considerano le unità""");
                }
            } while (InputManager.readYesOrNo("Vuoi inserire un altro ingrediente? (y)es/(n)o: "));

            final int portions = InputManager.readInt("Numero di porzioni che possono essere preparate con le dosi inserite: ", 1, Integer.MAX_VALUE);
            final float portionWorkload = InputManager.readFloat("Carico di lavoro per porzione: ", 0, 1);

            recipes.add(new Recipe(ingredients, portions, portionWorkload));
        });

        menu.run();
    }
}
