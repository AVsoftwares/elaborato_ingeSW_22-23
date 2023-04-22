package it.unibs.ui.manager.commands;

import it.unibs.core.Ingredient;
import it.unibs.core.Recipe;
import it.unibs.core.Restaurant;
import it.unibs.ui.Command;
import it.unibs.ui.InputManager;

public class CreateRecipeCommand implements Command {

    private final Restaurant restaurant;

    public CreateRecipeCommand(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void onSelection() {
        Recipe recipe = new Recipe();
        
        var recipeName = InputManager.readString("Inserisci il nome della ricetta: ");
        recipe.setName(recipeName);

        var exit = false;
        while (!exit) {
            final var name = InputManager.readString("Inserisci il nome dell'ingrediente: ");

            final var amount = InputManager.readFloat("Inserisci la quantità: ", 0, Float.MAX_VALUE);

            // TODO: validare l'unità di misura si accettano suggerimenti
            //potremmo mettere le più usate in una enum e confrontare oppure fare scegliere da console dopo averle listate
            final var unit = InputManager.readString("Inserisci l'unità di misura: ");

            recipe.addIngredient(new Ingredient(name, amount, unit));

            exit = !InputManager.readYesOrNo("Vuoi inserire un altro ingrediente? (y)es/(n)o: ");
        }


        recipe.setPortions(InputManager.readInt("Quante porzioni puoi produrre con le dosi che hai inserito?: "));

        recipe.setPersonWorkload(InputManager.readFloat("Qual è il carico di lavoro per porzione di questa ricetta?: ", 0, 1));

        restaurant.getRecipeBook().addRecipe(recipe);
        System.out.printf("La ricetta \"%s\" è stata inserita nel ricettario%n", recipe.getName());
    }
    
}
