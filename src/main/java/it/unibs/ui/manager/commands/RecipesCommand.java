package it.unibs.ui.manager.commands;

import it.unibs.core.Ingredient;
import it.unibs.core.Recipe;
import it.unibs.core.Restaurant;
import it.unibs.core.unit.MetricPrefix;
import it.unibs.core.unit.Quantity;
import it.unibs.ui.Command;
import it.unibs.ui.InputManager;
import it.unibs.ui.Menu;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RecipesCommand implements Command {

    private final Restaurant restaurant;

    @Override
    public void onSelection() {
        Menu menu = new Menu("Gestione ricette");

        menu.addEntry("Visualizza ricette", () -> {
            restaurant.getRecipes().forEach(r -> System.out.println("- " + r));
        });
        menu.addEntry("Aggiungi ricetta", () -> {
            final Recipe recipe = new Recipe();

            do {
                final var name = InputManager.readString("Nome dell'ingrediente: ");

                final var amount = InputManager.readFloat("Quantità dell'ingrediente: ", 0, Float.MAX_VALUE);

                final var unit = MetricPrefix.fromString(InputManager.readString("Unità di misura: "));

                recipe.addIngredient(new Ingredient(name), new Quantity(unit, amount));
            } while (InputManager.readYesOrNo("Vuoi inserire un altro ingrediente? (y)es/(n)o: "));


            recipe.setPortions(InputManager.readInt("Numero di porzioni che possono essere preparate con le dosi inserite: ", 1, Integer.MAX_VALUE));

            recipe.setPersonWorkload(InputManager.readFloat("Carico di lavoro per porzione: ", 0, 1));

            restaurant.addRecipe(recipe);
        });

        menu.run();
    }
}
